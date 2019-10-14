package com.gongbo.fss.bind.processor;


import com.gongbo.fss.bind.annotation.BindExtra;
import com.gongbo.fss.bind.util.ParamUtils;
import com.gongbo.fss.common.util.ReflectUtils;

import java.lang.reflect.Field;

public class BindParamProcessor {

    /**
     * 绑定参数
     *
     * @param obj
     * @param field
     * @param bindExtra
     */
    public static void bindParam(Object obj, Field field, BindExtra bindExtra) {
        Object value = ParamUtils.getParam(obj, bindExtra.name());
        if (value != null) {
            ReflectUtils.setFieldValue(obj, field, value);
        }
    }
}
