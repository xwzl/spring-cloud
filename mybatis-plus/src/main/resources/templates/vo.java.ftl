package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>

/**
* <p>
* ${table.comment!} 前端控制器
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
    class ${table.controllerName}sa<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {
    </#if>

    }
</#if>

package template;

#foreach($pkg in ${table.importPackages})
#end
#if(${swagger2})
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
#end
#if(${entityLombokModel})
#end
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
* <p>
    * ${entity}VO对象
    * </p>
*
* @author ${author}
* @since ${date}
*/
#if(${entityLombokModel})
@Data
#if(${superEntityClass})
#else
#end
#end
#if(${swagger2})
@ApiModel(value="${entity}对象", description="$!{table.comment}")
#end
#if(${superEntityClass})
public class ${entity} extends ${superEntityClass}#if(${activeRecord})<${entity}>#end {
#elseif(${activeRecord})
public class ${entity} extends Model<${entity}> {
#else
public class ${entity}VO implements Serializable {
#end

private static final long serialVersionUID = 1L;
## ----------  BEGIN 字段循环遍历  ----------
#foreach($field in ${table.fields})

#if(${field.keyFlag})
#set($keyPropertyName=${field.propertyName})
#end
#if("$!field.comment" != "")
#if(${swagger2})
@ApiModelProperty(value = "${field.comment}")
#else
/**
* ${field.comment}
*/
#end
#end
#if(${field.keyFlag})
## 主键
#if(${field.keyIdentityFlag})
##    @TableId(value = "${field.name}", type = IdType.AUTO)
#elseif(!$null.isNull(${idType}) && "$!idType" != "")
##    @TableId(value = "${field.name}", type = IdType.${idType})
#elseif(${field.convert})
##    @TableId("${field.name}")
#end
## 普通字段
#elseif(${field.fill})
## -----   存在字段填充设置   -----
#if(${field.convert})
##    @TableField(value = "${field.name}", fill = FieldFill.${field.fill})
#else
##    @TableField(fill = FieldFill.${field.fill})
#end
#elseif(${field.convert})
##    @TableField("${field.name}")
#end
## 乐观锁注解
#if(${versionFieldName}==${field.name})
##    @Version
#end
## 逻辑删除注解
#if(${logicDeleteFieldName}==${field.name})
##    @TableLogic
#end
###if(${field.propertyType.equals("Date")})
##    private String ${field.propertyName};
###else
private ${field.propertyType} ${field.propertyName};
###end
#end
## ----------  END 字段循环遍历  ----------

#if(!${entityLombokModel})
#foreach($field in ${table.fields})
#if(${field.propertyType.equals("boolean")})
#set($getprefix="is")
#else
#set($getprefix="get")
#end

public ${field.propertyType} ${getprefix}${field.capitalName}() {
return ${field.propertyName};
}

#if(${entityBuilderModel})
public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
#else
public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
#end
this.${field.propertyName} = ${field.propertyName};
#if(${entityBuilderModel})
return this;
#end
}
#end
#end

#if(${entityColumnConstant})
#foreach($field in ${table.fields})
public static final String ${field.name.toUpperCase()} = "${field.name}";

#end
#end
#if(${activeRecord})
@Override
protected Serializable pkVal() {
#if(${keyPropertyName})
return this.${keyPropertyName};
#else
return null;
#end
}

#end
#if(!${entityLombokModel})
@Override
public String toString() {
return "${entity}{" +
#foreach($field in ${table.fields})
#if($!{foreach.index}==0)
"${field.propertyName}=" + ${field.propertyName} +
#else
", ${field.propertyName}=" + ${field.propertyName} +
#end
#end
"}";
}
#end
}

