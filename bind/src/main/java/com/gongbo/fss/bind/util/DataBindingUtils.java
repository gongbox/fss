package com.gongbo.fss.bind.util;


import androidx.databinding.ViewDataBinding;

import com.gongbo.fss.common.util.ReflectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class DataBindingUtils {
    /**
     * 绑定databinding变量
     *
     * @param obj
     * @param bindingId
     * @param value
     */
    public static void bindingVariable(Object obj, String bindingFieldName, int bindingId, Object value) {
        Field bindingField = ReflectUtils.getField(obj.getClass(), bindingFieldName);
        ViewDataBinding binding = null;
        if (bindingField != null) {
            binding = ReflectUtils.getFieldValue(obj, bindingField);
        }
        if (binding != null) {
            if (value != null) {
                if (bindingId == -1) {
                    throw new RuntimeException("没有找到对应的bindingId");
                }
                binding.setVariable(bindingId, value);
            }
        }
    }

    /**
     * 绑定databinding变量
     *
     * @param obj
     * @param bindingName
     * @param value
     */
    public static void bindingVariable(Object obj, String bindingFieldName, String bindingName, Object value) {
        Field bindingField = ReflectUtils.getField(obj.getClass(), bindingFieldName);
        ViewDataBinding binding = null;
        if (bindingField != null) {
            binding = ReflectUtils.getFieldValue(obj, bindingField);
        }
        if (binding != null) {
            if (value != null) {
                String methodName = "set" + capitalizeString(bindingName);
                Method method = ReflectUtils.getMethod(binding.getClass(), methodName, value.getClass());
                if (method != null) {
                    ReflectUtils.methodInvoke(binding, method, value);
                } else {
                    throw new RuntimeException("在" + binding.getClass().getCanonicalName() + "类中找不到方法：" + methodName);
                }
            }
        }
    }


    /**
     * 首字母转化为大写
     *
     * @param string
     * @return
     */
    public static String capitalizeString(String string) {
        if (string == null) {
            return null;
        }
        if (string.isEmpty()) {
            return string;
        }
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
