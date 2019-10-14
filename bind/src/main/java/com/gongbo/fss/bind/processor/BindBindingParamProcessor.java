package com.gongbo.fss.bind.processor;


import com.gongbo.fss.bind.annotation.DataBindingExtra;
import com.gongbo.fss.bind.annotation.DataBindingExtras;
import com.gongbo.fss.bind.util.DataBindingUtils;
import com.gongbo.fss.bind.util.ParamUtils;
import com.gongbo.fss.common.util.ReflectUtils;

import java.lang.reflect.Field;

public class BindBindingParamProcessor {
    /**
     * 绑定参数到databinding变量
     *
     * @param obj
     */
    public static void bindBindingParam(Object obj) {
        DataBindingExtras dataBindingExtras = obj.getClass().getAnnotation(DataBindingExtras.class);
        if (dataBindingExtras != null) {
            for (DataBindingExtra dataBindingExtra : dataBindingExtras.value()) {
                bindBindingParam(obj, dataBindingExtra);
            }
        }
        DataBindingExtra dataBindingExtra = obj.getClass().getAnnotation(DataBindingExtra.class);
        if (dataBindingExtra != null) {
            bindBindingParam(obj, dataBindingExtra);
        }
    }

    /**
     * 绑定参数到databinding变量
     *
     * @param obj
     * @param field
     * @param dataBindingExtra
     */
    public static void bindBindingParam(Object obj, Field field, DataBindingExtra dataBindingExtra) {
        Object value = ParamUtils.getParam(obj, dataBindingExtra.name());
        if (value != null) {
            ReflectUtils.setFieldValue(obj, field, value);
        }
        DataBindingUtils.bindingVariable(obj, dataBindingExtra.bindingFieldName(), dataBindingExtra.id(), value);
    }

    /**
     * 绑定参数到databinding变量
     *
     * @param obj
     * @param dataBindingExtra
     */
    private static void bindBindingParam(Object obj, DataBindingExtra dataBindingExtra) {
        Object value = ParamUtils.getParam(obj, dataBindingExtra.name());
        DataBindingUtils.bindingVariable(obj, dataBindingExtra.bindingFieldName(), dataBindingExtra.id(), value);
    }


}
