package com.gongbo.fss.bind.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static class NameTypeValue {
        public String name;
        public String type;
        public boolean isField;
        public String[] values;

        @Override
        public String toString() {
            return "NameTypeValue{" +
                    "name='" + name + '\'' +
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

    public static NameTypeValue getNameTypeValue(String text) {
        NameTypeValue nameTypeValue = new NameTypeValue();
        nameTypeValue.name = getName(text);
        nameTypeValue.isField = isField(text);
        if (!nameTypeValue.isField) {
            nameTypeValue.type = getType(text);
        }
        nameTypeValue.values = getValues(text);
        return nameTypeValue;
    }

    public static void main(String[] args) {
        String text;
        //1,name:value
//        text = "name:1234";
//        text = "name:[1234]";
//        text = "name:[1234,12345]";
        //2,name:(Integer)value
//        text = "name:(Integer)123";
//        text = "name:(Integer)[123]";
//        text = "name:(Integer)[123,1234]";
        //3,:@value
        text = ":@value";
//        4,name:@value
//        text ="name:@value";
//        String name = getName("name:value");
//        System.out.println(name);
//        NameTypeValue nameTypeValue = getNameTypeValue(text);
//        System.out.println(nameTypeValue);
        boolean check = check(text);
        System.out.println(check);
        if (check) {
            NameTypeValue nameTypeValue = getNameTypeValue(text);
            System.out.println(nameTypeValue);
        }
    }
}
