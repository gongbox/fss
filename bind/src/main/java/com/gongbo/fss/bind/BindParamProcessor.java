package com.gongbo.fss.bind;


import com.gongbo.fss.bind.annotation.BindParam;
import com.gongbo.fss.common.util.ReflectUtils;

import java.lang.reflect.Field;

class BindParamProcessor {

    /**
     * 绑定参数
     *
     * @param obj
     * @param field
     * @param bindParam
     */
    static void bindParam(Object obj, Field field, BindParam bindParam) {
        Object value = ParamUtils.getParam(obj, bindParam.key());
        if (value != null) {
            ReflectUtils.setFieldValue(obj, field, value);
        }
    }
}
