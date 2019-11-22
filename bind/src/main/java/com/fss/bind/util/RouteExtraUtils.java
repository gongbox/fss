package com.fss.bind.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouteExtraUtils {

    public static class RouteExtraInfo {
        public String name;
        public String type;
        public boolean isField;
        public String[] values;

        @Override
        public String toString() {
            return "RouteExtraInfo{" +
                    "value='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", isField=" + isField +
                    ", values=" + Arrays.toString(values) +
                    '}';
        }
    }

    public static boolean check(String text) {
        if (!isField(text)) {
            Pattern pattern = Pattern.compile("^\\w+:(\\(\\w+\\))?\\[?[\\w,]+\\]?$");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                return true;
            }
        } else {
            Pattern pattern = Pattern.compile("^\\w*:@\\w+$");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                return true;
            }
        }


        return false;
    }

    public static String getName(String text) {
        Pattern pattern = Pattern.compile("^\\w+:");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String res = matcher.group();
            if (res.length() > 1) {
                return res.substring(0, res.length() - 1);
            }
            return res;
        }
        return null;
    }

    public static String[] getValues(String text) {
        Pattern pattern = Pattern.compile("\\[?[\\w,]+\\]?$");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String res = matcher.group();
            if (res.contains("[")) {
                return res.substring(1, res.length() - 1).split(",");
            } else {
                return new String[]{res};
            }
        }
        return null;
    }

    public static boolean isField(String text) {
        return text.contains("@");
    }

    public static String getType(String text) {
        Pattern pattern = Pattern.compile(":\\(\\w+\\)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String res = matcher.group();
            if (res.length() > 1) {
                return res.substring(2, res.length() - 1);
            }
            return res;
        }
        return null;
    }

    public static RouteExtraInfo getNameTypeValue(String text) {
        RouteExtraInfo routeExtraInfo = new RouteExtraInfo();
        routeExtraInfo.name = getName(text);
        routeExtraInfo.isField = isField(text);
        if (!routeExtraInfo.isField) {
            routeExtraInfo.type = getType(text);
        }
        routeExtraInfo.values = getValues(text);
        return routeExtraInfo;
    }

    public static void main(String[] args) {
        String text;
        //1,value:value
//        text = "value:1234";
//        text = "value:[1234]";
//        text = "value:[1234,12345]";
        //2,value:(Integer)value
//        text = "value:(Integer)123";
//        text = "value:(Integer)[123]";
//        text = "value:(Integer)[123,1234]";
        //3,:@value
        text = ":@value";
//        4,value:@value
//        text ="value:@value";
//        String value = getName("value:value");
//        System.out.println(value);
//        RouteExtraInfo nameTypeValue = getNameTypeValue(text);
//        System.out.println(nameTypeValue);
        boolean check = check(text);
        System.out.println(check);
        if (check) {
            RouteExtraInfo routeExtraInfo = getNameTypeValue(text);
            System.out.println(routeExtraInfo);
        }
    }
}
