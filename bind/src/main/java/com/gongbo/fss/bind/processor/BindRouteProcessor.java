package com.gongbo.fss.bind.processor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.gongbo.fss.bind.annotation.BindRoute;
import com.gongbo.fss.bind.annotation.BindRoutes;
import com.gongbo.fss.bind.annotation.BindRouteExtra;
import com.gongbo.fss.bind.util.ViewUtils;
import com.gongbo.fss.common.util.ReflectUtils;

import java.io.Serializable;
import java.lang.reflect.Field;


public class BindRouteProcessor {

    /**
     * 绑定路由
     *
     * @param obj
     */
    public static void bindRoute(Object obj) {
        BindRoutes bindRoutes = obj.getClass().getAnnotation(BindRoutes.class);
        if (bindRoutes != null) {
            for (BindRoute bindRoute : bindRoutes.value()) {
                bindRoute(obj, bindRoute);
            }
        }
        BindRoute bindRoute = obj.getClass().getAnnotation(BindRoute.class);
        if (bindRoute != null) {
            bindRoute(obj, bindRoute);
        }
    }

    /**
     * 绑定路由
     *
     * @param obj
     * @param bindRoute
     */
    private static void bindRoute(Object obj, BindRoute bindRoute) {
        View view = ViewUtils.getView(obj, bindRoute.id());
        if (view != null) {
            view.setOnClickListener(v -> {
                final Context currentContext;
                final Bundle bundle;
                Intent intent = new Intent();
                if (obj instanceof Context) {
                    currentContext = (Context) obj;
                } else if (obj instanceof Fragment) {
                    Fragment fragment = (Fragment) obj;
                    currentContext = fragment.getActivity();
                } else if (obj instanceof android.app.Fragment) {
                    android.app.Fragment fragment = (android.app.Fragment) obj;
                    currentContext = fragment.getActivity();
                } else {
                    throw new RuntimeException("无法获取到Context");
                }

                if (bindRoute.extraFields().length > 0) {
                    bundle = buildExtras(obj, bindRoute.extraFields());
                    intent.putExtras(bundle);
                } else {
                    bundle = null;
                }

                //要跳转的Activity
                Class<?> toClass = bindRoute.toActivity();
                //要跳转的action
                String action = bindRoute.action();
                //请求的requestCode
                final int requestCode = bindRoute.requestCode();
                //
                String[] categories = bindRoute.category();
                //
                int flags = bindRoute.flags();

                final int enterAnim = bindRoute.enterAnim();
                final int exitAnim = bindRoute.exitAnim();

                if (toClass != Object.class) {
                    intent.setClass(currentContext, toClass);
                }
                if (!TextUtils.isEmpty(action)) {
                    intent.setAction(action);
                }
                for (String category : categories) {
                    intent.addCategory(category);
                }

                if (-1 != flags) {
                    intent.setFlags(flags);
                } else if (!(currentContext instanceof Activity)) {    // Non activity, need less one flag.
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }

                // Navigation in main looper.
                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {

                        if (requestCode > 0) {  // Need start for result
                            ActivityCompat.startActivityForResult((Activity) currentContext, intent, requestCode, bundle);
                        } else {
                            ActivityCompat.startActivity(currentContext, intent, bundle);
                        }

                        if ((0 != enterAnim || 0 != exitAnim) && currentContext instanceof Activity) {    // Old version.
                            ((Activity) currentContext).overridePendingTransition(enterAnim, exitAnim);
                        }
                    }
                });

            });
        } else {
            throw new RuntimeException("绑定Route失败，原因：在" + obj.getClass().getCanonicalName() + "类中获取不到id为" + bindRoute.id() + "的view");
        }
    }

    /**
     * 构造参数
     *
     * @param obj
     * @param extraFields
     * @return
     */
    private static Bundle buildExtras(Object obj, String[] extraFields) {
        Bundle bundle = new Bundle();
        for (String fieldName : extraFields) {
            Field field = ReflectUtils.getField(obj.getClass(), fieldName);
            if (field != null) {
                Object value = ReflectUtils.getFieldValue(obj, field);
                if (value instanceof Serializable) {
                    BindRouteExtra bindRouteExtra = field.getAnnotation(BindRouteExtra.class);
                    String key = bindRouteExtra == null ? fieldName : bindRouteExtra.key();
                    bundle.putSerializable(key, (Serializable) value);
                } else {
                    throw new RuntimeException("构造参数失败，原因：" + obj.getClass().getCanonicalName() + "类中的字段" + fieldName + "没有序列化");
                }
            } else {
                throw new RuntimeException("构造参数失败，原因：在" + obj.getClass().getCanonicalName() + "类中获取不到字段名为" + fieldName + "的字段");
            }
        }
        return bundle;
    }
}
