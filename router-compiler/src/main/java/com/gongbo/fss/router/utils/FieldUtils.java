package com.gongbo.fss.router.utils;

import com.squareup.javapoet.FieldSpec;

import java.util.List;

public class FieldUtils {
    public static boolean containsField(List<FieldSpec> fieldSpecs, String name) {
        if (fieldSpecs == null) return false;
        if (name == null) return false;
        for (FieldSpec fieldSpec : fieldSpecs) {
            if (fieldSpec.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static String getFieldName(List<FieldSpec> fieldSpecs, String name) {
        int i = 1;
        String newName = name;
        while (containsField(fieldSpecs, newName)) {
            newName = name + i++;
        }
        return name;
    }
}
