package com.gongbo.fss.bind.util;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;


import com.gongbo.fss.common.util.ReflectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClickUtils {
    /**
     * 绑定点击事件
     *
     * @param obj
     * @param ids
     * @param method
     */
    public static void bindOnClick(@NonNull Object obj, @IdRes int[] ids, @NonNull Method method) {
        View.OnClickListener onClickListener = view -> {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }

            try {
                if (method.getParameterTypes().length == 0) {
                    method.invoke(obj);
                } else if (method.getParameterTypes().length == 1 && method.getParameterTypes()[0].isAssignableFrom(view.getClass())) {
                    method.invoke(obj, view);
                } else {
                    throw new RuntimeException("BindOnClick只能绑定在没有参数或只有一个参数（该参数类型必须是触发点击事件的控件的类或父类）的方法上");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        };
        for (int id : ids) {
            View view = ViewUtils.getView(obj, id);
            if (view != null) {
                view.setOnClickListener(onClickListener);
            }
        }
    }

    /**
     * 绑定点击事件
     *
     * @param obj
     * @param ids
     * @param methodName
     */
    public static void bindOnClick(Object obj, @IdRes int[] ids, String methodName) {
        if (!methodName.isEmpty()) {
            Method method = ReflectUtils.findMethod(obj.getClass(), methodName, new Class[]{}, new Class[]{View.class});
            if (method != null) {
                bindOnClick(obj, ids, method);
            } else {
                throw new RuntimeException("无法绑定点击事件，原因：在类" + obj.getClass().getCanonicalName() + "中找不到方法" + methodName);
            }
        }
    }
}
