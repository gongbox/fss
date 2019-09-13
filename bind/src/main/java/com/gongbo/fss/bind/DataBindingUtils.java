package com.gongbo.fss.bind;



import androidx.databinding.ViewDataBinding;

import com.gongbo.fss.common.util.ReflectUtils;

import java.lang.reflect.Field;


class DataBindingUtils {
    /**
     * 绑定databinding变量
     *
     * @param obj
     * @param bindingId
     * @param value
     */
    static void bindingVariable(Object obj, int bindingId, Object value) {
        Field bindingField = ReflectUtils.getField(obj.getClass(), "binding");
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
