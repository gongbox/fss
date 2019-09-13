package com.fss.router.api.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.fss.router.annotation.Autowired;
import com.fss.router.api.log.ILogger;
import com.fss.router.api.manager.RouteManager;
import com.fss.router.api.util.ReflectUtils;

import java.lang.reflect.Field;

public class FssRouter {

    public static final String AUTO_INJECT = "wmHzgDrsfdsdfs4241";

    //是否开启日志
    private boolean openLog = false;

    private boolean openDebug = false;

    private boolean autoInject = false;

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

    public void enableAutoInject() {
        autoInject = true;
    }

    public boolean canAutoInject() {
        return autoInject;
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
                    String name = TextUtils.isEmpty(autowired.value()) ? field.getName() : autowired.value();
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

    public static ILogger logger = new ILogger() {

        @Override
        public void showLog(boolean isShowLog) {

        }

        @Override
        public void showStackTrace(boolean isShowStackTrace) {

        }

        @Override
        public void debug(String tag, String message) {
            if (isShowLog) {
                Log.d(tag, message);
            }
        }

        @Override
        public void info(String tag, String message) {
            if (isShowLog) {
                Log.i(tag, message);
            }
        }

        @Override
        public void warning(String tag, String message) {
            if (isShowLog) {
                Log.w(tag, message);
            }
        }

        @Override
        public void error(String tag, String message) {
            if (isShowLog) {
                Log.e(tag, message);
            }
        }

        @Override
        public void monitor(String message) {
        }

        @Override
        public boolean isMonitorMode() {
            return false;
        }

        @Override
        public String getDefaultTag() {
            return null;
        }
    };
}
