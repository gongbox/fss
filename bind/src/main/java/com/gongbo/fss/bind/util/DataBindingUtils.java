package com.gongbo.fss.bind.util;


import androidx.databinding.ViewDataBinding;

import com.gongbo.fss.common.util.ReflectUtils;

import java.lang.reflect.Field;


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
                    throw new RuntimeException("");
                }
                binding.setVariable(bindingId, value);
            }
        }
    }
}
