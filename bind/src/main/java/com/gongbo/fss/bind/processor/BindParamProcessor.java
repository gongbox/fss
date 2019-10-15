package com.gongbo.fss.bind.processor;


import android.text.TextUtils;

import com.gongbo.fss.bind.annotation.BindParam;
import com.gongbo.fss.bind.util.ParamUtils;
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
        Object value = ParamUtils.getParam(obj, name);
        if (value != null) {
            ReflectUtils.setFieldValue(obj, field, value);
        }
    }
}
