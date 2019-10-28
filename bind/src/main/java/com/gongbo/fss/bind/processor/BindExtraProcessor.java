package com.gongbo.fss.bind.processor;


import android.text.TextUtils;

import com.gongbo.fss.bind.annotation.BindExtra;
import com.gongbo.fss.bind.annotation.BindExtras;
import com.gongbo.fss.bind.util.DataBindingUtils;
import com.gongbo.fss.bind.util.ParamUtils;
import com.gongbo.fss.common.util.ReflectUtils;

import java.lang.reflect.Field;

public class BindExtraProcessor {
    /**
     * 绑定参数到databinding变量
     *
     * @param obj
     */
    public static void bindExtra(Object obj) {
        BindExtras bindExtras = obj.getClass().getAnnotation(BindExtras.class);
        if (bindExtras != null) {
            for (BindExtra bindExtra : bindExtras.value()) {
                bindExtra(obj, bindExtra);
            }
        }
        BindExtra bindExtra = obj.getClass().getAnnotation(BindExtra.class);
        if (bindExtra != null) {
            bindExtra(obj, bindExtra);
        }
    }

    /**
     * 绑定参数到databinding变量
     *
     * @param obj
     * @param field
     * @param bindExtra
     */
    public static void bindExtra(Object obj, Field field, BindExtra bindExtra) {
        String name = bindExtra.name();
        if (TextUtils.isEmpty(name)) {
            name = field.getName();
        }
        Object value = ParamUtils.getParam(obj, name);
        if (value != null) {
            ReflectUtils.setFieldValue(obj, field, value);
        }
        //必须设置id才可以绑定databinding变量
        if (bindExtra.id() != -1) {
            DataBindingUtils.bindingVariable(obj, bindExtra.bindingFieldName(), bindExtra.id(), value);
        } else if (!TextUtils.isEmpty(bindExtra.bindingName())) {
            DataBindingUtils.bindingVariable(obj, bindExtra.bindingFieldName(), bindExtra.bindingName(), value);
        }
    }

    /**
     * 绑定参数到databinding变量
     *
     * @param obj
     * @param bindExtra
     */
    private static void bindExtra(Object obj, BindExtra bindExtra) {
        String name = bindExtra.name();
        if (TextUtils.isEmpty(name)) {
            throw new RuntimeException("在类" + obj.getClass().getCanonicalName() + "上的BindExtra注解必须添加name属性！");
        }
        Object value = ParamUtils.getParam(obj, name);
        if (bindExtra.id() != -1) {
            DataBindingUtils.bindingVariable(obj, bindExtra.bindingFieldName(), bindExtra.id(), value);
        } else if (!TextUtils.isEmpty(bindExtra.bindingName())) {
            DataBindingUtils.bindingVariable(obj, bindExtra.bindingFieldName(), bindExtra.bindingName(), value);
        }
    }


}
