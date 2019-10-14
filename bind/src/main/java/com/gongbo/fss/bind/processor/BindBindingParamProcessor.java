package com.gongbo.fss.bind.processor;


import com.gongbo.fss.bind.annotation.BindBindingExtra;
import com.gongbo.fss.bind.annotation.BindBindingExtras;
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
        BindBindingExtras bindBindingExtras = obj.getClass().getAnnotation(BindBindingExtras.class);
        if (bindBindingExtras != null) {
            for (BindBindingExtra bindBindingExtra : bindBindingExtras.value()) {
                bindBindingParam(obj, bindBindingExtra);
            }
        }
        BindBindingExtra bindBindingExtra = obj.getClass().getAnnotation(BindBindingExtra.class);
        if (bindBindingExtra != null) {
            bindBindingParam(obj, bindBindingExtra);
        }
    }

    /**
     * 绑定参数到databinding变量
     *
     * @param obj
     * @param field
     * @param bindBindingExtra
     */
    public static void bindBindingParam(Object obj, Field field, BindBindingExtra bindBindingExtra) {
        Object value = ParamUtils.getParam(obj, bindBindingExtra.name());
        if (value != null) {
            ReflectUtils.setFieldValue(obj, field, value);
        }
        DataBindingUtils.bindingVariable(obj, bindBindingExtra.bindingFieldName(), bindBindingExtra.id(), value);
    }

    /**
     * 绑定参数到databinding变量
     *
     * @param obj
     * @param bindBindingExtra
     */
    private static void bindBindingParam(Object obj, BindBindingExtra bindBindingExtra) {
        Object value = ParamUtils.getParam(obj, bindBindingExtra.name());
        DataBindingUtils.bindingVariable(obj, bindBindingExtra.bindingFieldName(), bindBindingExtra.id(), value);
    }


}
