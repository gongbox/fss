package com.fss.router.api.manager;

import android.content.Intent;
import android.util.SparseArray;

import com.fss.router.api.callback.OnActivityResult;
import com.fss.router.api.handler.RouteHandler;
import com.fss.router.api.interceptor.IInterceptor;
import com.fss.router.api.launcher.FssRouter;
import com.fss.router.api.model.RouteMeta;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


public class RouteManager {

    private static SparseArray<OnActivityResult> callbacks = new SparseArray<>();

    private static List<IInterceptor> interceptors = new ArrayList<>();


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
     * 添加拦截器
     *
     * @param interceptor
     */
    public static void addInterceptor(IInterceptor interceptor) {
        interceptors.add(interceptor);
    }

    /**
     * 获取拦截
     *
     * @return
     */
    static List<IInterceptor> getInterceptors() {
        return interceptors;
    }

    /**
     * 执行并判断是否需要拦截
     *
     * @param routeMeta
     * @return
     */
    public static boolean doIntercept(RouteMeta routeMeta) {
        for (IInterceptor interceptor : interceptors) {
            if (interceptor != null) {
                if (interceptor.intercept(routeMeta)) {
                    FssRouter.logger.info("FssRouter", "路由被拦截：" + routeMeta);
                    return true;
                }
            }
        }
        return false;
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
    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        OnActivityResult callback = getOnActivityResultCallback(requestCode);
        if (callback != null) {
            callback.onActivityResult(requestCode, resultCode, data);
            removeOnActivityResultCallback(requestCode);
        }
    }

}
