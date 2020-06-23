package com.java.prepare.until.mongo;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author xuweizhi
 * @since 2020/05/13 16:28
 */
public class MongoRegexUtil {

    public static Pattern exactMatch(String keyWord) {
        keyWord = makeQueryStringAllRegExp(keyWord);
        return Pattern.compile("^foresee$".replace("foresee", keyWord), Pattern.CASE_INSENSITIVE);
    }

    public static Pattern rightMatch(String keyWord) {
        keyWord = makeQueryStringAllRegExp(keyWord);
        return Pattern.compile("^.*foresee$".replace("foresee", keyWord), Pattern.CASE_INSENSITIVE);
    }

    public static Pattern leftMatch(String keyWord) {
        keyWord = makeQueryStringAllRegExp(keyWord);
        return Pattern.compile("^foresee.*$".replace("foresee", keyWord), Pattern.CASE_INSENSITIVE);
    }

    public static Pattern fuzzyMatching(String keyWord) {
        keyWord = makeQueryStringAllRegExp(keyWord);
        return Pattern.compile("^.*foresee.*$".replace("foresee", keyWord), Pattern.CASE_INSENSITIVE);
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{} \\需要第一个替换，否则replace方法替换时会有逻辑bug
     */
    public static String makeQueryStringAllRegExp(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }

        return str.replace("\\", "\\\\").replace("*", "\\*").replace("+", "\\+").replace("|", "\\|").replace("{", "\\{")
            .replace("}", "\\}").replace("(", "\\(").replace(")", "\\)").replace("^", "\\^").replace("$", "\\$")
            .replace("[", "\\[").replace("]", "\\]").replace("?", "\\?").replace(",", "\\,").replace(".", "\\.")
            .replace("&", "\\&");
    }

}
