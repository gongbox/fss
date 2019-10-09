package com.gongbo.fss.bind.processor;


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
        Object value = ParamUtils.getParam(obj, bindParam.key());
        if (value != null) {
            ReflectUtils.setFieldValue(obj, field, value);
        }
    }
}
