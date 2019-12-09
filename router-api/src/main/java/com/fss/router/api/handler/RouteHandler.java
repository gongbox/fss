package com.fss.router.api.handler;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.fss.router.annotation.Data;
import com.fss.router.annotation.DefaultExtra;
import com.fss.router.annotation.DefaultExtras;
import com.fss.router.annotation.Extra;
import com.fss.router.annotation.RouteActivity;
import com.fss.router.annotation.RouteFragment;
import com.fss.router.annotation.RouteService;
import com.fss.router.api.callback.OnActivityResult;
import com.fss.router.api.manager.RouteManager;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public class RouteHandler implements InvocationHandler {

    private static final String TAG = "RouteHandler";

    private static final Logger logger = Logger.getLogger(RouteHandler.class.getSimpleName());

    private static final int ACTIVITY = 0;
    private static final int SERVICE = 1;
    private static final int FRAGMENT = 5;

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, objects);
        }

        //获取请求的Route信息
        RouteActivity routeActivity;
        RouteService routeService = null;
        RouteFragment routeFragment = null;
        Uri data = null;

        int routeType = -1;
        if ((routeActivity = method.getAnnotation(RouteActivity.class)) != null) {
            routeType = ACTIVITY;
        } else if ((routeService = method.getAnnotation(RouteService.class)) != null) {
            routeType = SERVICE;
        } else if ((routeFragment = method.getAnnotation(RouteFragment.class)) != null) {
            routeType = FRAGMENT;
        } else {
            throw new Exception("this function must be declared with RouteActivity or RouteService annotation!");
        }

        //检查第一个参数是否为Context类型对象
        if (!(objects[0] instanceof Context)) {
            throw new Exception("the first param must be Context!");
        }
        final Context currentContext = (Context) objects[0];

        final Intent intent = new Intent();
        final Bundle bundle = new Bundle();
        //获取DefaultExtra注解
        List<DefaultExtra> defaultExtras = getDefaultExtraAnnotations(method);

        for (DefaultExtra defaultExtra : defaultExtras) {
            String name = defaultExtra.name();
            //判断默认值是否不为空
            String defaultValue = defaultExtra.defaultValue();

            //如果defaultValue不为空且name不为空
            if (!TextUtils.isEmpty(defaultValue) && !TextUtils.isEmpty(name)) {
                //向intent中添加一组值
                addExtra(bundle, name, formatValue(defaultValue, defaultExtra.type()));
            }
        }

        //如果有参数传递
        if (objects.length > 1) {
            //获取参数注解信息
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            //获取参数类型信息
            Class<?>[] parameterTypes = method.getParameterTypes();
            foreach_param:
            for (int i = 1; i < parameterAnnotations.length; i++) {
                //如果参数类型为 OnActivityResult 并且 requestCode 大于0 时
                if (parameterTypes[i] == OnActivityResult.class && routeType == ACTIVITY && routeActivity.requestCode() > 0 && objects[i] != null) {
                    RouteManager.setOnActivityResultCallback(routeActivity.requestCode(), (OnActivityResult) objects[i]);
                    continue;
                }

                Annotation[] annotations = parameterAnnotations[i];
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Extra) {
                        //向intent中添加一组值
                        addExtra(bundle, ((Extra) annotation).value(), objects[i]);
                        continue foreach_param;
                    } else if (annotation instanceof Data) {
                        if (parameterTypes[i] == Uri.class) {
                            data = (Uri) objects[i];
                        }
                        continue foreach_param;
                    }
                }

                if (parameterTypes[i] == Uri.class) {
                    data = (Uri) objects[i];
                }
            }
        }

        switch (routeType) {
            case ACTIVITY:

                Log.i(TAG, bundle.toString());

                // Build intent
                intent.putExtras(bundle);

                //要跳转的Activity
                Class<?> toClass = routeActivity.value();
                if (toClass == void.class && !routeActivity.destination().isEmpty()) {
                    toClass = Class.forName(routeActivity.destination());
                }
                //要跳转的action
                String action = routeActivity.action();
                //请求的requestCode
                final int requestCode = routeActivity.requestCode();
                //
                String[] categories = routeActivity.category();
                //
                int flags = routeActivity.flags();

                String type = routeActivity.type();

                final int enterAnim = routeActivity.enterAnim();
                final int exitAnim = routeActivity.exitAnim();

                if (toClass != void.class) {
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

                if (!TextUtils.isEmpty(type)) {
                    if (data != null) {
                        intent.setDataAndType(data, type);
                    } else {
                        intent.setType(type);
                    }
                }

                //如果返回类型为intent时，直接返回该值
                if (method.getReturnType() == Intent.class) {
                    return intent;
                }

                // Navigation in main looper.
                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {

                        if (requestCode > 0) {  // Need start for result
                            ActivityCompat.startActivityForResult((Activity) currentContext, intent, requestCode, null);
                        } else {
                            ActivityCompat.startActivity(currentContext, intent, null);
                        }

                        if ((0 != enterAnim || 0 != exitAnim) && currentContext instanceof Activity) {    // Old version.
                            ((Activity) currentContext).overridePendingTransition(enterAnim, exitAnim);
                        }
                    }
                });

                break;
            case FRAGMENT:
                if (method.getReturnType() == void.class) {
                    return null;
                }
                Class fragmentMeta = routeFragment.value();
                if (fragmentMeta == void.class && !routeActivity.destination().isEmpty()) {
                    fragmentMeta = Class.forName(routeActivity.destination());
                }
                if (fragmentMeta != void.class) {
                    try {
                        Object instance = fragmentMeta.getConstructor().newInstance();
                        if (instance instanceof Fragment) {
                            ((Fragment) instance).setArguments(bundle);
                        } else if (instance instanceof android.support.v4.app.Fragment) {
                            ((android.support.v4.app.Fragment) instance).setArguments(bundle);
                        }
                        return instance;
                    } catch (Exception ex) {

                    }
                }
                break;
            case SERVICE:
                // Build intent
                intent.putExtras(bundle);

                //要跳转的Activity
                toClass = routeService.value();
                if (toClass == void.class && !routeActivity.destination().isEmpty()) {
                    toClass = Class.forName(routeActivity.destination());
                }
                //要跳转的action
                action = routeService.action();

                if (toClass != void.class) {
                    intent.setClass(currentContext, toClass);
                }
                if (!TextUtils.isEmpty(action)) {
                    intent.setAction(action);
                }
                currentContext.startService(intent);
                break;
            default:
                return null;
        }

        return null;
    }

    /**
     * 获取DefaultExtra注解
     *
     * @param method
     * @return
     */
    private List<DefaultExtra> getDefaultExtraAnnotations(Method method) {
        List<DefaultExtra> defaultExtras = new ArrayList<>();
        DefaultExtras defaultExtraContainer = method.getAnnotation(DefaultExtras.class);
        if (defaultExtraContainer != null && defaultExtraContainer.value().length > 0) {
            defaultExtras.addAll(Arrays.asList(defaultExtraContainer.value()));
        }
        DefaultExtra defaultExtra = method.getAnnotation(DefaultExtra.class);
        if (defaultExtra != null) {
            defaultExtras.add(defaultExtra);
        }
        return defaultExtras;
    }


    /**
     * 向intent中添加一组值
     *
     * @param intent
     * @param name
     * @param value
     */
    private static void addExtra(Intent intent, String name, Object value) {
        if (value instanceof Serializable) {
            intent.putExtra(name, (Serializable) value);
        } else if (value instanceof Parcelable) {
            intent.putExtra(name, (Parcelable) value);
        }
    }

    /**
     * 向intent中添加一组值
     *
     * @param bundle
     * @param name
     * @param value
     */
    private static void addExtra(Bundle bundle, String name, Object value) {
        if (value instanceof Serializable) {
            bundle.putSerializable(name, (Serializable) value);
        } else if (value instanceof Parcelable) {
            bundle.putParcelable(name, (Parcelable) value);
        }
    }

    /**
     * 格式化字符串为某一类型值
     *
     * @param value
     * @param valueType
     * @return
     */
    private static Object formatValue(String value, Class<?> valueType) {
        if (value == null) {
            return null;
        } else if (valueType == String.class) {
            return value;
        } else if (valueType == Boolean.class || valueType == boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (valueType == Byte.class || valueType == byte.class) {
            return Byte.parseByte(value);
        } else if (valueType == Character.class || valueType == char.class) {
            return value.charAt(0);
        } else if (valueType == Short.class || valueType == short.class) {
            return Short.parseShort(value);
        } else if (valueType == Integer.class || valueType == int.class) {
            return Integer.parseInt(value);
        } else if (valueType == Long.class || valueType == long.class) {
            return Long.parseLong(value);
        } else if (valueType == Float.class || valueType == float.class) {
            return Float.parseFloat(value);
        } else if (valueType == Double.class || valueType == double.class) {
            return Double.parseDouble(value);
        }
        return null;
    }

}
