package template;

{package.Controller};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
#if(${restControllerStyle})
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
#else
import org.springframework.stereotype.Controller;
#end
#if(${superControllerClassPackage})
import ${superControllerClassPackage};
#end
import ${package.Service}.${table.serviceName};
import com.mybatis.plus.demo.pojo.${entity}Form;
import com.mybatis.plus.demo.pojo.${entity}VO;
import xxx.ResponseBean;
import xxx.BaseForm;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * ${entity}前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
#if(${restControllerStyle})
@RestController
#else
@Controller
#end
@Api(tags = "${entity}")
@RequestMapping("#if(${package.ModuleName})/${package.ModuleName}#end/#if(${controllerMappingHyphenStyle})${controllerMappingHyphen}#else${table.entityPath}#end")
#if(${kotlin})
class ${table.controllerName}#if(${superControllerClass}) : ${superControllerClass}()#end

#else
#if(${superControllerClass})
public class ${table.controllerName} extends ${superControllerClass} {
#else
public class ${table.controllerName} {
#end
    @Autowired
    public ${table.serviceName} ${table.entityPath}Service;

    /**
    * 保存单条
    * @param param 保存参数
    * @return 是否添加成功
    */
    @ApiOperation(value = "保存", notes = "保存数据到${entity}")
    @RequestMapping(value = "/add.json", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseBean add${entity}(@RequestBody BaseForm<${entity}Form> param) {
        Integer result = ${table.entityPath}Service.save(param.getData());
        return new ResponseBean(result);
    }

    /**
    * 更新(根据主键id更新)
    * @param param 修改参数
    * @return 是否更改成功
    */
    @ApiOperation(value = "更新数据", notes = "根据主键id更新${entity}数据")
    @RequestMapping(value = "/updateById.json", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseBean update${entity}ById(@RequestBody BaseForm<${entity}Form> param) {
        Integer result = ${table.entityPath}Service.updateById(param.getData());
        return new ResponseBean(result);
    }

    /**
    * 删除(根据主键id伪删除)
    * @param param 主键id
    * @return 是否删除成功
    */
    @ApiOperation(value = "删除数据", notes = "根据主键id伪删除${entity}数据")
    @RequestMapping(value = "/deleteById.json", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseBean delete${entity}ById(@RequestBody BaseForm<String> param) {
        Integer result = ${table.entityPath}Service.deleteById(param.getData());
        return new ResponseBean(result);
    }

    /**
    * 根据主键id查询单条
    * @param param 主键id
    * @return 查询结果
    */
    @ApiOperation(value = "获取单条数据", notes = "根据主键id获取${entity}数据")
    @RequestMapping(value = "/getById.json", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseBean get${entity}ById(@RequestBody BaseForm<String> param) {
        ${entity}VO result = ${table.entityPath}Service.selectById(param.getData());
        return new ResponseBean(result);
    }

    /**
    * 查询全部
    * @param param 查询条件
    * @return 查询结果
    */
    @ApiOperation(value = "全部查询", notes = "查询${entity}全部数据")
    @RequestMapping(value = "/queryAll.json", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseBean get${entity}All(@RequestBody BaseForm<${entity}Form> param) {
        List<${entity}VO> result = ${table.entityPath}Service.selectAll(param.getData());
        return new ResponseBean(result);
    }

    /**
    * 分页查询
    * @param param 查询条件
    * @return 查询结果
    */
    @ApiOperation(value = "分页查询", notes = "分页查询${entity}全部数据")
    @RequestMapping(value = "/queryPage.json", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseBean get${entity}Page(@RequestBody BaseForm<${entity}Form> param) {
        IPage<${entity}VO> result = ${table.entityPath}Service.selectPage(param.getData());
        return new ResponseBean(result);
    }



}

#end
