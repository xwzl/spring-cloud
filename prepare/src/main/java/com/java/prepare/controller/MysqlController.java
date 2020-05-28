package com.java.prepare.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.java.prepare.mapper.TagMapper;
import com.java.prepare.model.TagDO;
import com.java.prepare.model.mongo.MongoTag;
import com.java.prepare.model.mongo.MysqlIdBackUp;
import com.java.prepare.model.vos.BaseTagVO;
import com.java.prepare.until.MongoPageUtil;
import com.java.prepare.until.MongoRegexUtil;
import com.java.prepare.until.MysqlTable;
import com.spring.common.model.common.ApiResult;
import com.spring.common.model.common.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xuweizhi
 * @since 2020/05/29 3:29
 */
@Api("mysql")
@RestController
@RequestMapping("mysql")
public class MysqlController {

    @Resource
    private TagMapper tagMapper;

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 获取标签列表
     *
     * @param keywords 关键字
     * @param pageNum  页数
     * @param pageSize 大小
     * @return 返回值
     */
    @GetMapping("contentTagList")
    @ApiOperation("获取标签列表")
    public ApiResult<PageVO<BaseTagVO>> contentTagList(@RequestParam(value = "keyWords", required = false) String keywords,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Criteria criteria1 = Criteria.where("isDelete").is(0);

        // Criteria criteria = QueryHelper.criteria();
        if (StringUtils.isNotEmpty(keywords)) {
          criteria1.and("other").regex(MongoRegexUtil.fuzzyMatching(keywords));
        }
        Query other = Query.query(criteria1);
        other.with(Sort.by(Sort.Direction.ASC, "searchId", "rank"));
        PageVO<MongoTag> result = MongoPageUtil.pageUtil(other, MongoTag.class, pageNum, pageSize, mongoTemplate);
        PageVO<BaseTagVO> pageVO = new PageVO<>();
        pageVO.setPageNum(pageNum);
        pageVO.setTotalNum(result.getTotalNum());
        pageVO.setPageSize(pageSize);
        pageVO.setList(new ArrayList<>());
        if (CollectionUtils.isEmpty(result.getList())) {
            return new ApiResult<>(pageVO);
        }
        List<BaseTagVO> list = getBaseTagVOS(result.getList());
        pageVO.setList(list);
        return new ApiResult<>(pageVO);
    }

    private List<BaseTagVO> getBaseTagVOS(List<MongoTag> result1) {
        List<BaseTagVO> list = new ArrayList<>();
        BaseTagVO baseTagVO = null;
        for (MongoTag mongoTag : result1) {
            baseTagVO = new BaseTagVO();
            baseTagVO.setId(mongoTag.getId().toString());
            baseTagVO.setLevel(mongoTag.getParentId() != null ? 2 : 1);
            baseTagVO.setTag(mongoTag.getTag());
            if (mongoTag.getParentId() != null) {
                baseTagVO.setParentId(mongoTag.getParentId());
            }
            list.add(baseTagVO);
        }
        return list;
    }

    @PostMapping("migrateTag")
    public ApiResult<String> migrateTag() {
        // 父级标签
        List<TagDO> tagDOS = tagMapper.parentSelect();
        tagDOS.forEach(tagDO -> {
            Long id = tagDO.getId();
            MysqlIdBackUp mysqlIdBackUp = new MysqlIdBackUp(id, MysqlTable.TAG, 1);
            MongoTag parentDO = new MongoTag();
            try {
                tagDO.setId(null);
                BeanUtil.copyProperties(tagDO, parentDO);
                if (StringUtils.isNotEmpty(tagDO.getOther())) {
                    parentDO.setOther(JSONArray.parseArray(tagDO.getOther(), String.class));
                }
                // 保存腹肌标签
                mongoTemplate.save(parentDO);
                String mongoId = parentDO.getId().toString();
                mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(mongoId)),
                        Update.update("searchId", mongoId), MongoTag.class);
                mysqlIdBackUp.setMongoId(mongoId);
                // 过滤 mongo
                mongoTemplate.save(mysqlIdBackUp);
                updateUpdate(id, mongoId);
            } catch (Exception e) {
                updateBackUp(mysqlIdBackUp);
            }
        });
        return null;
    }

    private void updateUpdate(Long parentId, String mongoParentId) {
        // 字标签
        List<com.java.prepare.model.TagDO> tagDOS = tagMapper.childSelect(parentId);
        tagDOS.forEach(tagDO -> {
            Long id = tagDO.getId();
            MysqlIdBackUp mysqlIdBackUp = new MysqlIdBackUp(id, MysqlTable.TAG, 1);
            MongoTag parentDO = new MongoTag();
            try {
                tagDO.setId(null);
                BeanUtil.copyProperties(tagDO, parentDO);
                if (StringUtils.isNotEmpty(tagDO.getOther())) {
                    parentDO.setOther(Collections.singletonList(tagDO.getOther()));
                }
                parentDO.setSearchId(mongoParentId);
                parentDO.setParentId(mongoParentId);
                // 保存父级标签
                mongoTemplate.save(parentDO);
                String mongoId = parentDO.getId().toString();
                mysqlIdBackUp.setMongoId(mongoId);
                // 过滤 mongo
                mongoTemplate.save(mysqlIdBackUp);
            } catch (Exception e) {
                updateBackUp(mysqlIdBackUp);
            }
        });
    }

    private void updateBackUp(MysqlIdBackUp mysqlIdBackUp) {
        mysqlIdBackUp.setSuccessState(2);
        mongoTemplate.save(mysqlIdBackUp);
    }
}
