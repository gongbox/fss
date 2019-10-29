package com.gongbo.fss.bind.processor;


import android.text.TextUtils;

import com.gongbo.fss.bind.annotation.BindParam;
import com.gongbo.fss.bind.exception.NotFoundExtraException;
import com.gongbo.fss.bind.util.ExtraUtils;
import com.gongbo.fss.common.util.ReflectUtils;

import java.lang.reflect.Field;

public class BindParamProcessor {

    /**
     * 绑定参数
     *
     * @param obj
     * @param field
     * @param bindParam
     */
    public static void bindParam(Object obj, Field field, BindParam bindParam) {
        String name = bindParam.name();
        //如果没有声明name属性，则使用字段名作为name值
        if (TextUtils.isEmpty(name)) {
            name = field.getName();
        }
        //参数
        Object value;
        try {
            value = ExtraUtils.getExtra(obj, name);
        } catch (NotFoundExtraException e) {
            //必须存在时抛出异常
            if (bindParam.required()) {
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
    }
}
