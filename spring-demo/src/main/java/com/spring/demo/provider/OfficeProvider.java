package com.spring.demo.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author xuweizhi
 * @since 2019/08/05 22:58
 */
public class OfficeProvider {

    /**
     * 如果 mapper 有 @Param 参数注解不需要在此方法中额外添加参数
     *
     * @return
     */
    public String provider(/*final String brand, final String assetType*/) {
        //SQL query = new SQL();
        //query.SELECT("*");
        //query.FROM("hyy_office_computer");
        //query.WHERE("emp_level = #{empLevel}");
        //query.AND();
        //query.WHERE("emp_name = #{emp_name}");
        //System.out.println(query.toString());
        //return query.toString();

        return new SQL() {{
            SELECT("*");
            FROM("hyy_office_computer");
            //if (StringUtils.isNotEmpty(brand)) {
                WHERE("brand = #{brand}");
            //}
            //if (StringUtils.isNotEmpty(assetType)) {
                WHERE("asset_type = #{assetType}");
            //}
        }}.toString();
    }

    //private String selectPersonSql() {
    //    return new SQL() {{
    //        SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
    //        SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
    //        FROM("PERSON P");
    //        FROM("ACCOUNT A");
    //        INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
    //        INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
    //        WHERE("P.ID = A.ID");
    //        WHERE("P.FIRST_NAME like ?");
    //        OR();
    //        WHERE("P.LAST_NAME like ?");
    //        GROUP_BY("P.ID");
    //        HAVING("P.LAST_NAME like ?");
    //        OR();
    //        HAVING("P.FIRST_NAME like ?");
    //        ORDER_BY("P.ID");
    //        ORDER_BY("P.FULL_NAME");
    //    }}.toString();
    //}

    // Anonymous inner class
    //public String deletePersonSql() {
    //    return new SQL() {{
    //        DELETE_FROM("PERSON");
    //        WHERE("ID = #{id}");
    //    }}.toString();
    //}
    //
    //// Builder / Fluent style
    //public String insertPersonSql() {
    //    String sql = new SQL()
    //            .INSERT_INTO("PERSON")
    //            .VALUES("ID, FIRST_NAME", "#{id}, #{firstName}")
    //            .VALUES("LAST_NAME", "#{lastName}")
    //            .toString();
    //    return sql;
    //}
    //
    //// With conditionals (note the final parameters, required for the anonymous inner class to access them)
    //public String selectPersonLike(final String id, final String firstName, final String lastName) {
    //    return new SQL() {{
    //        SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME");
    //        FROM("PERSON P");
    //        if (id != null) {
    //            WHERE("P.ID like #{id}");
    //        }
    //        if (firstName != null) {
    //            WHERE("P.FIRST_NAME like #{firstName}");
    //        }
    //        if (lastName != null) {
    //            WHERE("P.LAST_NAME like #{lastName}");
    //        }
    //        ORDER_BY("P.LAST_NAME");
    //    }}.toString();
    //}
    //
    //public String deletePersonSql() {
    //    return new SQL() {{
    //        DELETE_FROM("PERSON");
    //        WHERE("ID = #{id}");
    //    }}.toString();
    //}
    //
    //public String insertPersonSql() {
    //    return new SQL() {{
    //        INSERT_INTO("PERSON");
    //        VALUES("ID, FIRST_NAME", "#{id}, #{firstName}");
    //        VALUES("LAST_NAME", "#{lastName}");
    //    }}.toString();
    //}
    //
    //public String updatePersonSql() {
    //    return new SQL() {{
    //        UPDATE("PERSON");
    //        SET("FIRST_NAME = #{firstName}");
    //        WHERE("ID = #{id}");
    //    }}.toString();
    //}

    //public String selectPersonSql() {
    //    return new SQL()
    //            .SELECT("P.ID", "A.USERNAME", "A.PASSWORD", "P.FULL_NAME", "D.DEPARTMENT_NAME", "C.COMPANY_NAME")
    //            .FROM("PERSON P", "ACCOUNT A")
    //            .INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID", "COMPANY C on D.COMPANY_ID = C.ID")
    //            .WHERE("P.ID = A.ID", "P.FULL_NAME like #{name}")
    //            .ORDER_BY("P.ID", "P.FULL_NAME")
    //            .toString();
    //}
    //
    //public String insertPersonSql() {
    //    return new SQL()
    //            .INSERT_INTO("PERSON")
    //            .INTO_COLUMNS("ID", "FULL_NAME")
    //            .INTO_VALUES("#{id}", "#{fullName}")
    //            .toString();
    //}
    //
    //public String updatePersonSql() {
    //    return new SQL()
    //            .UPDATE("PERSON")
    //            .SET("FULL_NAME = #{fullName}", "DATE_OF_BIRTH = #{dateOfBirth}")
    //            .WHERE("ID = #{id}")
    //            .toString();
    //}
    //
    //public String insertPersonsSql() {
    //    // INSERT INTO PERSON (ID, FULL_NAME)
    //    //     VALUES (#{mainPerson.id}, #{mainPerson.fullName}) , (#{subPerson.id}, #{subPerson.fullName})
    //    return new SQL()
    //            .INSERT_INTO("PERSON")
    //            .INTO_COLUMNS("ID", "FULL_NAME")
    //            .INTO_VALUES("#{mainPerson.id}", "#{mainPerson.fullName}")
    //            .ADD_ROW()
    //            .INTO_VALUES("#{subPerson.id}", "#{subPerson.fullName}")
    //            .toString();
    //}
    //
    //public String selectPersonsWithOffsetLimitSql() {
    //    // SELECT id, name FROM PERSON
    //    //     LIMIT #{limit} OFFSET #{offset}
    //    return new SQL()
    //            .SELECT("id", "name")
    //            .FROM("PERSON")
    //            .LIMIT("#{limit}")
    //            .OFFSET("#{offset}")
    //            .toString();
    //}
    //
    //public String selectPersonsWithFetchFirstSql() {
    //    // SELECT id, name FROM PERSON
    //    //     OFFSET #{offset} ROWS FETCH FIRST #{limit} ROWS ONLY
    //    return new SQL()
    //            .SELECT("id", "name")
    //            .FROM("PERSON")
    //            .OFFSET_ROWS("#{offset}")
    //            .FETCH_FIRST_ROWS_ONLY("#{limit}")
    //            .toString();
    //}
}
