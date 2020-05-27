package com.spring.common.model.utils;

import cn.hutool.db.sql.SqlFormatter;

/**
 * SqlUtils工具类
 *
 * @author Caratacus
 * @since 2016-11-13
 */
public class SqlUtils {

    private final static SqlFormatter SQL_FORMATTER = new SqlFormatter();

    /**
     * 格式sql
     */
    @Deprecated
    public static String sqlFormat(String boundSql, boolean format) {
        if (format) {
            try {
                return SQL_FORMATTER.format(boundSql);
            } catch (Exception ignored) {
            }
        }
        return boundSql;
    }

}
