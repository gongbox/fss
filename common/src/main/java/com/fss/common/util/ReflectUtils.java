package com.fss.common.util;


import com.fss.common.exception.ExecuteException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by $USER_NAME on 2018/12/27.
 */
public class ReflectUtils {
    /**
     * 设置静态变量对的值
     *
     * @param field
     * @param value
     */
    public static void setFieldValue(Field field, Object value) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            field.set(null, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置对象某一个字段的值
     *
     * @param obj
     * @param field
     * @param value
     */
    public static void setFieldValue(Object obj, Field field, Object value) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象某一个字段的值
     *
     * @param field
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> T getFieldValue(Object obj, Field field) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            return (T) field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取静态变量的值
     *
     * @param field
     * @param <T>
     * @return
     */
    public static <T> T getFieldValue(Field field) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            return (T) field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 调用方法
     *
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object methodInvoke(Object obj, Method method, Object... args) {
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        try {
            return method.invoke(obj, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new ExecuteException(e.getCause());
        }
        return null;
    }

    /**
     * 查找字段
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Field getField(Class clazz, String fieldName) {
        Field field = null;
        try {
            field = clazz.getField(fieldName);
        } catch (NoSuchFieldException e) {
        }
        if (field != null) return field;
        for (; clazz != Object.class; ) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
            }
            if (field != null) return field;
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    /**
     * 查找方法
     *
     * @param clazz
     * @param methodName
     * @param parameterTypes
     * @return
     */
    public static Method getMethod(Class clazz, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        try {
            method = clazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
        }
        if (method != null) return method;
        for (; clazz != Object.class; ) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
            }
            if (method != null) return method;
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    /**
     * 查找方法
     *
     * @param clazz
     * @param methodName
     * @param parameterTypesArray
     * @return
     */
    public static Method findMethod(Class clazz, String methodName, Class<?>[]... parameterTypesArray) {
        Method method = null;
        for (Class<?>[] parameterTypes : parameterTypesArray) {
            try {
                method = clazz.getMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException ignored) {
            }
            if (method != null) return method;
        }
        for (; clazz != Object.class; ) {
            for (Class<?>[] parameterTypes : parameterTypesArray) {
                try {
                    method = clazz.getDeclaredMethod(methodName, parameterTypes);
                } catch (NoSuchMethodException ignored) {
                }
                if (method != null) return method;
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    /**
     * 比较是否为同一个字段
     *
     * @param field1
     * @param field2
     * @return
     */
    public static boolean compare(Field field1, Field field2) {
        return (field1.getName().equals(field2.getName()))
                && (field1.getType() == field2.getType());
    }

    /**
     * 比较两个方法是否相等
     *
     * @param method1
     * @param method2
     * @return
     */
    public static boolean compare(Method method1, Method method2) {
        if (!method1.getName().equals(method2.getName())) {
            return false;
        }
        if (method1.getParameterTypes().length != method2.getParameterTypes().length) {
            return false;
        }
        for (int i = 0, size = method1.getParameterTypes().length; i < size; i++) {
            if (method1.getParameterTypes()[i] != method2.getParameterTypes()[i]) {
                return false;
            }
        }
        return true;
    }
}
