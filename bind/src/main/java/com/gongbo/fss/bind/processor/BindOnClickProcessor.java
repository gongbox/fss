package com.gongbo.fss.bind.processor;


import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.bind.annotation.BindOnClicks;
import com.gongbo.fss.bind.util.ClickUtils;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BindOnClickProcessor {

    /**
     * 绑定点击事件
     * @param obj
     */
    public static void bindOnClick(Object obj) {
        BindOnClicks bindOnClicks = obj.getClass().getAnnotation(BindOnClicks.class);
        if (bindOnClicks != null) {
            for (BindOnClick bindOnClick : bindOnClicks.value()) {
                bindOnClick(obj, bindOnClick);
            }
        }
        BindOnClick bindOnClick = obj.getClass().getAnnotation(BindOnClick.class);
        if (bindOnClick != null) {
            bindOnClick(obj, bindOnClick);
        }
    }

    /**
     * 绑定点击事件
     *
     * @param obj
     * @param id
     * @param method
     */
    public static void bindOnClick(Object obj, int[] id, Method method) {
        ClickUtils.bindOnClick(obj, id, method);
    }

    /**
     * 绑定点击事件
     *
     * @param obj
     * @param bindOnClick
     */
    private static void bindOnClick(Object obj, BindOnClick bindOnClick) {
        ClickUtils.bindOnClick(obj, bindOnClick.value(), bindOnClick.onClickMethod());
    }

    /**
     * 绑定点击事件
     *
     * @param obj
     * @param method
     * @param bindOnClicks
     */
    public static void bindOnClick(Object obj, final Method method, List<BindOnClick> bindOnClicks) {
        Set<Integer> ids = new HashSet<>();
        for (BindOnClick bindOnClick : bindOnClicks) {
            for (int id : bindOnClick.value()) {
                ids.add(id);
            }
        }
        int[] array = new int[ids.size()];
        int index = 0;
        for (int id : ids) {
            array[index++] = id;
        }
        ClickUtils.bindOnClick(obj, array, method);
    }
}
