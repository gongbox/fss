package com.gongbo.fss.router.utils;

public class StringUtils {
    /**
     * 将字符串数组合并为字符串
     *
     * @param start
     * @param end
     * @param strings
     * @param delimeter
     * @return
     */
    public static String joinString(String start, String end, String[] strings, String delimeter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(start);
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append("\"").append(strings[i]).append("\"");
            if (i < strings.length - 1) {
                stringBuilder.append(delimeter);
            }
        }
        stringBuilder.append(end);
        return stringBuilder.toString();
    }

    /**
     * 将int数组合并为字符串
     *
     * @param start
     * @param end
     * @param strings
     * @param delimeter
     * @return
     */
    public static String joinString(String start, String end, int[] strings, String delimeter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(start);
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
            if (i < strings.length - 1) {
                stringBuilder.append(delimeter);
            }
        }
        stringBuilder.append(end);
        return stringBuilder.toString();
    }

    /**
     * 将驼峰命名转化为常量命名
     *
     * @param name
     * @return
     */
    public static String formatToStaticField(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = name.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append(chars[i]);
            if (i < chars.length - 1 && chars[i] >= 'a' && chars[i] <= 'z' && chars[i + 1] >= 'A' && chars[i + 1] <= 'Z') {
                stringBuilder.append("_");
            }
        }

        return stringBuilder.toString().toUpperCase();
    }

    /**
     * 首字母转化为大写
     *
     * @param string
     * @return
     */
    public static String capitalizeString(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static String formatApiFieldName(String apiName) {
        if (apiName.startsWith("I")) {
            return apiName.substring(1);
        }
        return apiName;
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0;
    }
}
