package com.fss.router.utils;

import com.squareup.javapoet.FieldSpec;

import java.util.ArrayList;
import java.util.List;

public class FieldUtils {

    public static String getFieldName(List<FieldSpec> fieldSpecs, String name) {
        if (fieldSpecs == null) return name;
        List<String> names = new ArrayList<String>(fieldSpecs.size());
        for (FieldSpec fieldSpec : fieldSpecs) {
            names.add(fieldSpec.name);
        }

        int i = 2;
        String newName = name;
        while (names.contains(newName)) {
            newName = name + "_" + i++;
        }
        return newName;
    }
}
