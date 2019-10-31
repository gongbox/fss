package com.gongbo.fss.bind.processor;


import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.util.ClickUtils;

public class BindActivityProcessor {


    /**
     * 绑定finish事件
     *
     * @param obj
     */
    public static void bindFinish(Object obj) {
        BindActivity bindActivity = obj.getClass().getAnnotation(BindActivity.class);
        if (bindActivity != null && bindActivity.finishViewId().length > 0) {
            bindFinish(obj, bindActivity);
        }
    }

    /**
     * 绑定finish事件
     *
     * @param obj
     * @param bindActivity
     */
    private static void bindFinish(Object obj, BindActivity bindActivity) {
        ClickUtils.bindOnClick(obj, bindActivity.finishViewId(), "finish");
    }
}
