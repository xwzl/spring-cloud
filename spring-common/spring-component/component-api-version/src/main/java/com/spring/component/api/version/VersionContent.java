package com.spring.component.api.version;

import java.util.regex.Pattern;

public class VersionContent {

    public static final String VERSION_HEADER_KEY = "apiVersion";
    static final int p0 = 10000;
    static final int p1 = 100;
    static final int p2 = 1;
    static final String patternStr = "[0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{1,2}";
    static final Pattern pattern = Pattern.compile(patternStr);

    static Integer calculate(String version) {
        String[] strs = version.split("\\.");
        return Integer.parseInt(strs[0]) * p0 + Integer.parseInt(strs[1]) * p1 + Integer.parseInt(strs[2]) * p2;
    }

    public static boolean isVersionWellFormed(String version) {
        return pattern.matcher(version).matches();
    }
}
