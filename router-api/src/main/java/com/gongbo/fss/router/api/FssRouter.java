package com.gongbo.fss.router.api;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;


import com.gongbo.fss.router.annotation.Autowired;
import com.gongbo.fss.router.api.util.ReflectUtils;

import java.lang.reflect.Field;

public class FssRouter {

    //是否开启日志
    private boolean openLog = false;

    private boolean openDebug = false;

    public boolean isOpenLog() {
        return openLog;
    }

    public void setOpenLog(boolean openLog) {
        this.openLog = openLog;
    }

    public boolean isOpenDebug() {
        return openDebug;
    }

    public void setOpenDebug(boolean openDebug) {
        this.openDebug = openDebug;
    }

    private static FssRouter fssRouter = new FssRouter();

    public static FssRouter getInstance() {
        return fssRouter;
    }

    /**
     * @param object
     * @param intent
     */
    public void reject(Object object, Intent intent) {
        Bundle extras;
        if (object != null && intent != null && (extras = intent.getExtras()) != null) {
            for (Field field : object.getClass().getDeclaredFields()) {
                Autowired autowired = field.getAnnotation(Autowired.class);
                if (autowired != null) {
                    //获取name
                    String name = TextUtils.isEmpty(autowired.name()) ? field.getName() : autowired.name();
                    //获取value
                    Object value = extras.get(name);
                    if (value != null) {
                        ReflectUtils.setFieldValue(object, field, value);
                    } else if (autowired.required()) {
                        throw new RuntimeException("the field:" + field.getName() + " reject failed!");
                    }
                }
            }
        }
    }

    /**
     * 处理回调函数
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        RouteManager.onActivityResult(requestCode, resultCode, data);
    }
}
