package com.gongbo.fss.bind.processor;


import android.text.TextUtils;

import com.gongbo.fss.bind.annotation.BindExtra;
import com.gongbo.fss.bind.annotation.BindExtras;
import com.gongbo.fss.bind.exception.NotFoundExtraException;
import com.gongbo.fss.bind.util.DataBindingUtils;
import com.gongbo.fss.bind.util.ExtraUtils;
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
     * 绑定参数到databinding变量,同时给对应字段赋值
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
        //参数
        Object value;
        try {
            value = ExtraUtils.getExtra(obj, name);
        } catch (NotFoundExtraException e) {
            //必须存在时抛出异常
            if (bindExtra.required()) {
                throw new RuntimeException("绑定参数失败：在" +
                        obj.getClass().getCanonicalName() +
                        "中没有获取到key值为" + name + "的参数，\n请确保该参数存在，或者设置对应字段上的BindExtra注解的required属性为false");
            }
            return; //没有获取到值则返回，就不为变量赋值，也不绑定
        }

        //如果字段不为空，则为该字段赋值
        if (field != null) {
            ReflectUtils.setFieldValue(obj, field, value);
        }

        //如果id值不为-1，或者bindingName不为空，则绑定databinding变量
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
    public static void bindExtra(Object obj, BindExtra bindExtra) {
        bindExtra(obj, null, bindExtra);
    }

}
