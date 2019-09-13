package com.gongbo.fss.router.api;

import android.content.Intent;
import android.util.SparseArray;

import com.gongbo.fss.router.api.callback.OnActivityResult;

import java.lang.reflect.Proxy;


public class RouteManager {

    private static SparseArray<OnActivityResult> callbacks = new SparseArray<>();


    private static RouteHandler routeHandler = new RouteHandler();

    /**
     * 创建代理对象
     *
     * @param interfaces
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T createRouteApi(Class<?>... interfaces) {
        return (T) Proxy.newProxyInstance(FssRouter.class.getClassLoader(), interfaces, routeHandler);
    }

    /**
     * 根据requestCode获取OnActivityResult回调函数
     *
     * @param requestCode
     * @return
     */
    public static OnActivityResult getOnActivityResultCallback(Integer requestCode) {
        return callbacks.get(requestCode);
    }

    /**
     * 设置回调函数
     *
     * @param requestCode
     * @param onActivityResult
     */
    public static void setOnActivityResultCallback(Integer requestCode, OnActivityResult onActivityResult) {
        callbacks.put(requestCode, onActivityResult);
    }

    /**
     * 移除回调函数
     *
     * @param requestCode
     */
    public static void removeOnActivityResultCallback(Integer requestCode) {
        callbacks.remove(requestCode);
    }

    /**
     * 处理回调函数
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void onActivityResult(int requestCode, int resultCode,  Intent data) {
        OnActivityResult callback = getOnActivityResultCallback(requestCode);
        if (callback != null) {
            callback.onActivityResult(requestCode, resultCode, data);
            removeOnActivityResultCallback(requestCode);
        }
    }

}
