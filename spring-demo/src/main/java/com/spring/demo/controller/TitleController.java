package com.spring.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.common.model.common.ResultVO;
import com.spring.demo.config.http.HttpApiService;
import com.spring.demo.model.Title;
import com.spring.demo.service.TitleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 获取 vip 课程
 *
 * @author xuweizhi
 * @since 2019-10-20
 */
@Slf4j
@RestController
@RequestMapping("/title")
//@Api(tags = "C 语言编程网 vip 课程")
public class TitleController {

    @Resource
    private TitleService titleService;

    @Resource
    private HttpApiService httpApiService;

    @GetMapping
    //@ApiOperation("获取 c 语言网 VIP 课程")
    public ResultVO<List<Title>> list(String title) {
        List<Title> list = titleService.list(new QueryWrapper<Title>()
                .lambda().like(Title::getTitle, title));
        return new ResultVO<>(list);
    }

    @GetMapping("lists")
    //@ApiOperation("查询")
    public ResultVO<List<Title>> lists(@RequestParam("ids") List<String> ids) {
        return new ResultVO<>(titleService.listByIds(ids));
    }

    /*@PostMapping
    //@ApiOperation("爬取 C 语言 VIP 课程")
    public void save(@Validated @NotEmpty(message = "不能为空") @RequestBody Integer i) {
        String urlBase = "http://c.biancheng.net/view/%d.html";
        int end = i + 500;
        for (int j = i; j < end; j++) {
            String url = String.format(urlBase, j);
            try {
                //if (j % 25 == 0) {
                //    Thread.sleep(100);
                //}
                String s = httpApiService.doGet(url);
                String title = s.substring(s.indexOf("<title>") + 7, s.indexOf("</title>"));
                Title title1 = new Title().setTitle(title).setUrl(url);
                titleService.save(title1);
            } catch (Exception e) {
                log.info(j + "失败");
            }
        }
    }*/
}
