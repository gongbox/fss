package com.gongbo.fss.bind.processor;

import android.view.View;


import com.gongbo.fss.bind.util.*;

import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.common.util.ReflectUtils;

import java.lang.reflect.Field;

public class BindViewProcessor {

    /**
     * 绑定view
     *
     * @param obj
     * @param field
     * @param bindView
     */
    public static void bindView(Object obj, Field field, BindView bindView) {
        View view = ViewUtils.getView(obj, bindView.id());
        if (view != null) {
            ReflectUtils.setFieldValue(obj, field, view);
        } else {
            throw new RuntimeException("绑定view失败，原因：在" + obj.getClass().getCanonicalName() + "类中获取不到id为" + bindView.id() + "的view");
        }
    }

    /**
     * 绑定view
     *
     * @param obj
     * @param field
     * @param bindView
     */
    public static void bindView(Object obj, View v, Field field, BindView bindView) {
        View view = ViewUtils.getView(v, bindView.id());
        if (view != null) {
            ReflectUtils.setFieldValue(obj, field, view);
        } else {
            throw new RuntimeException("绑定view失败，原因：在" + obj.getClass().getCanonicalName() + "类中获取不到id为" + bindView.id() + "的view");
        }
    }
}
