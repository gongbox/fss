package com.gongbo.fss.bind;


import com.gongbo.fss.bind.annotation.BindActivity;

import static com.gongbo.fss.bind.OnClickUtils.bindOnClick;

class BindActivityProcessor {


    /**
     * 绑定finish事件
     *
     * @param obj
     */
    static void bindFinish(Object obj) {
        BindActivity bindActivity = obj.getClass().getAnnotation(BindActivity.class);
        if (bindActivity != null && bindActivity.finish().length > 0) {
            bindFinish(obj, bindActivity);
        }
    }

    /**
     * 绑定finish事件
     *
     * @param obj
     * @param bindActivity
     */
    static void bindFinish(Object obj, BindActivity bindActivity) {
        bindOnClick(obj, bindActivity.finish(), "finish");
    }
}
