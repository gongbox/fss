package com.gongbo.fss.bind.processor;


import com.gongbo.fss.bind.annotation.BindBindingParam;
import com.gongbo.fss.bind.annotation.BindBindingParams;
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
        BindBindingParams bindBindingParams = obj.getClass().getAnnotation(BindBindingParams.class);
        if (bindBindingParams != null) {
            for (BindBindingParam bindBindingParam : bindBindingParams.value()) {
                bindBindingParam(obj, bindBindingParam);
            }
        }
        BindBindingParam bindBindingParam = obj.getClass().getAnnotation(BindBindingParam.class);
        if (bindBindingParam != null) {
            bindBindingParam(obj, bindBindingParam);
        }
    }

    /**
     * 绑定参数到databinding变量
     *
     * @param obj
     * @param field
     * @param bindBindingParam
     */
    public static void bindBindingParam(Object obj, Field field, BindBindingParam bindBindingParam) {
        Object value = ParamUtils.getParam(obj, bindBindingParam.key());
        if (value != null) {
            ReflectUtils.setFieldValue(obj, field, value);
        }
        DataBindingUtils.bindingVariable(obj, bindBindingParam.bindingFieldName(), bindBindingParam.id(), value);
    }

    /**
     * 绑定参数到databinding变量
     *
     * @param obj
     * @param bindBindingParam
     */
    private static void bindBindingParam(Object obj, BindBindingParam bindBindingParam) {
        Object value = ParamUtils.getParam(obj, bindBindingParam.key());
        DataBindingUtils.bindingVariable(obj, bindBindingParam.bindingFieldName(), bindBindingParam.id(), value);
    }


}