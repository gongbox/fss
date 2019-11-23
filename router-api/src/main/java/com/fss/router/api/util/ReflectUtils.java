package com.fss.router.api.util;

import java.lang.reflect.Field;

public class ReflectUtils {
    /**
     * 反射获取对象某一字段值
     *
     * @param object
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object object, String fieldName) {
        Field field = null;
        try {
            field = object.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (field != null) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                return field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 反射设置属性值
     *
     * @param object
     * @param field
     * @param value
     */
    public static void setFieldValue(Object object, Field field, Object value) {
        if (value != null) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                field.set(object, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
