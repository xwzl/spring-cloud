package ${package.DTO};

<#if entityLombokModel>
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;
</#if>

/**
 * ${table.comment}
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@Accessors(chain = true)
public class ${table.dtoName} implements Serializable {

private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.comment!?length gt 0>

        /**
        * ${field.comment}
        */
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
}
