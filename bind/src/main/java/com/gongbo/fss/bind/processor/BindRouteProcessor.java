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
import com.gongbo.fss.bind.util.RegexUtils;
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

                if (bindRoute.extras().length > 0) {
                    bundle = buildExtras(obj, bindRoute.extras());
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
     * @param extras
     * @return
     */
    private static Bundle buildExtras(Object obj, String[] extras) {
        Bundle bundle = new Bundle();
        for (String extra : extras) {
            if (!RegexUtils.check(extra)) {
                throw new RuntimeException("you declared a wrong pattern:" + extra);
            }
            RegexUtils.NameTypeValue nameTypeValue = RegexUtils.getNameTypeValue(extra);
            if (!nameTypeValue.isField && nameTypeValue.values != null && nameTypeValue.values.length > 0) {
                String[] values = nameTypeValue.values;
                if (nameTypeValue.type != null && !nameTypeValue.type.isEmpty()) {
                    switch (nameTypeValue.type.toLowerCase()) {
                        case "int":
                        case "integer":
                        case "java.lang.integer": {
                            int[] formatValues = new int[values.length];
                            for (int i = 0; i < nameTypeValue.values.length; i++) {
                                if (values[i] == null || "null".equals(values[i])) {
                                    formatValues[i] = 0;
                                } else {
                                    formatValues[i] = Integer.parseInt(values[i]);
                                }
                            }
                            if (formatValues.length > 1) {
                                bundle.putIntArray(nameTypeValue.name, formatValues);
                            } else {
                                bundle.putInt(nameTypeValue.name, formatValues[0]);
                            }
                        }
                        break;
                        case "long":
                        case "java.lang.long": {
                            long[] formatValues = new long[values.length];
                            for (int i = 0; i < nameTypeValue.values.length; i++) {
                                if (values[i] == null || "null".equals(values[i])) {
                                    formatValues[i] = 0L;
                                } else {
                                    formatValues[i] = Long.parseLong(values[i]);
                                }
                            }
                            if (formatValues.length > 1) {
                                bundle.putLongArray(nameTypeValue.name, formatValues);
                            } else {
                                bundle.putLong(nameTypeValue.name, formatValues[0]);
                            }
                        }
                        break;
                        case "float":
                        case "java.lang.float": {
                            float[] formatValues = new float[values.length];
                            for (int i = 0; i < nameTypeValue.values.length; i++) {
                                if (values[i] == null || "null".equals(values[i])) {
                                    formatValues[i] = 0.0f;
                                } else {
                                    formatValues[i] = Float.parseFloat(values[i]);
                                }
                            }
                            if (formatValues.length > 1) {
                                bundle.putFloatArray(nameTypeValue.name, formatValues);
                            } else {
                                bundle.putFloat(nameTypeValue.name, formatValues[0]);
                            }
                        }
                        break;
                        case "double":
                        case "java.lang.double": {
                            double[] formatValues = new double[values.length];
                            for (int i = 0; i < nameTypeValue.values.length; i++) {
                                if (values[i] == null || "null".equals(values[i])) {
                                    formatValues[i] = 0L;
                                } else {
                                    formatValues[i] = Double.parseDouble(values[i]);
                                }
                            }
                            if (formatValues.length > 1) {
                                bundle.putDoubleArray(nameTypeValue.name, formatValues);
                            } else {
                                bundle.putDouble(nameTypeValue.name, formatValues[0]);
                            }
                        }
                        break;
                        case "char":
                        case "java.lang.char": {
                            char[] formatValues = new char[values.length];
                            for (int i = 0; i < nameTypeValue.values.length; i++) {
                                if (values[i] == null || "null".equals(values[i])) {
                                    formatValues[i] = (char) 0;
                                } else {
                                    formatValues[i] = values[i].charAt(0);
                                }
                            }
                            if (formatValues.length > 1) {
                                bundle.putCharArray(nameTypeValue.name, formatValues);
                            } else {
                                bundle.putChar(nameTypeValue.name, formatValues[0]);
                            }
                        }
                        break;
                        case "byte":
                        case "java.lang.byte": {
                            byte[] formatValues = new byte[values.length];
                            for (int i = 0; i < nameTypeValue.values.length; i++) {
                                if (values[i] == null || "null".equals(values[i])) {
                                    formatValues[i] = (char) 0;
                                } else {
                                    formatValues[i] = Byte.parseByte(values[i]);
                                }
                            }
                            if (formatValues.length > 1) {
                                bundle.putByteArray(nameTypeValue.name, formatValues);
                            } else {
                                bundle.putByte(nameTypeValue.name, formatValues[0]);
                            }
                        }
                        break;
                        case "string":
                        case "java.lang.string":
                            if (values.length > 1) {
                                bundle.putStringArray(nameTypeValue.name, values);
                            } else {
                                bundle.putString(nameTypeValue.name, values[0]);
                            }
                            break;
                        default:
                            break;
                    }
                } else {
                    if (values.length > 1) {
                        bundle.putStringArray(nameTypeValue.name, values);
                    } else {
                        bundle.putString(nameTypeValue.name, values[0]);
                    }
                }
            } else if (nameTypeValue.isField) {
                String fieldName = nameTypeValue.values[0];
                String name = nameTypeValue.name == null || nameTypeValue.name.isEmpty() ? fieldName : nameTypeValue.name;

                Field field = ReflectUtils.getField(obj.getClass(), fieldName);
                if (field != null) {
                    Object value = ReflectUtils.getFieldValue(obj, field);
                    if (value instanceof Serializable) {
                        bundle.putSerializable(name, (Serializable) value);
                    } else {
                        throw new RuntimeException("构造参数失败，原因：" + obj.getClass().getCanonicalName() + "类中的字段" + extra + "没有序列化");
                    }
                } else {
                    throw new RuntimeException("构造参数失败，原因：在" + obj.getClass().getCanonicalName() + "类中获取不到字段名为" + extra + "的字段");
                }
            }
        }
        return bundle;
    }
}
