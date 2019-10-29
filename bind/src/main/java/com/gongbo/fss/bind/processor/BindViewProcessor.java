package com.gongbo.fss.bind.processor;

import android.view.View;

import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.bind.util.ViewUtils;
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
        //获取view
        View view = ViewUtils.getView(obj, bindView.id());
        //设置view
        setView(obj, view, field, bindView);
    }

    /**
     * 绑定view
     *
     * @param obj
     * @param field
     * @param bindView
     */
    public static void bindView(Object obj, View v, Field field, BindView bindView) {
        //获取view
        View view = ViewUtils.getView(v, bindView.id());
        //设置view
        setView(obj, view, field, bindView);
    }

    /**
     * 设置view
     *
     * @param obj
     * @param view
     * @param field
     * @param bindView
     */
    private static void setView(Object obj, View view, Field field, BindView bindView) {
        if (view != null) {
            ReflectUtils.setFieldValue(obj, field, view);
        } else if (bindView.required()) {
            throw new RuntimeException("无法绑定View：在" + obj.getClass().getCanonicalName()
                    + "类中试图为" + field.getName() + "字段绑定id为" + bindView.id() +
                    "的view，但无法找到这个View，\n请确保这个View在布局中存在，或者设置对应字段上的BindView注解的required属性为false");
        }
    }
}
