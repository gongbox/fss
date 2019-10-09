package com.gongbo.fss.bind.processor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
                Activity activity = null;
                Bundle bundle = null;
                int requestCode = bindRoute.requestCode();
                if (obj instanceof Activity) {
                    activity = (Activity) obj;
                } else if (obj instanceof Fragment) {
                    Fragment fragment = (Fragment) obj;
                    activity = fragment.getActivity();
                } else if (obj instanceof android.app.Fragment) {
                    android.app.Fragment fragment = (android.app.Fragment) obj;
                    activity = fragment.getActivity();
                }

                if (activity == null) {
                    throw new RuntimeException("无法获取到Activity");
                }

                Intent intent = new Intent(activity, bindRoute.toActivity());
                if (bindRoute.extraFields().length > 0) {
                    bundle = buildExtras(obj, bindRoute.extraFields());
                    intent.putExtras(bundle);
                }

                if (requestCode != -1) {
                    activity.startActivityForResult(intent, requestCode);
                } else {
                    activity.startActivity(intent);
                }
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
