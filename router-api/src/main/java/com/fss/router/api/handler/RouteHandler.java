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
import com.fss.router.annotation.RouteApi;
import com.fss.router.annotation.RouteFragment;
import com.fss.router.annotation.RouteService;
import com.fss.router.api.callback.OnActivityResult;
import com.fss.router.api.manager.RouteManager;
import com.fss.router.api.model.RouteMeta;
import com.fss.router.enums.RouteType;

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

        final RouteMeta routeMeta = new RouteMeta();

        //获取请求的Route信息
        RouteApi routeApi;

        RouteActivity routeActivity;
        RouteService routeService = null;
        RouteFragment routeFragment = null;

        if ((routeActivity = method.getAnnotation(RouteActivity.class)) != null) {
            routeMeta.setRouteType(RouteType.ACTIVITY);
        } else if ((routeService = method.getAnnotation(RouteService.class)) != null) {
            routeMeta.setRouteType(RouteType.SERVICE);
        } else if ((routeFragment = method.getAnnotation(RouteFragment.class)) != null) {
            routeMeta.setRouteType(RouteType.FRAGMENT);
        } else {
            throw new Exception("this function must be declared with RouteActivity or RouteService annotation!");
        }

        //检查第一个参数是否为Context类型对象
        if (!(objects[0] instanceof Context)) {
            throw new Exception("the first param must be Context!");
        }

        routeApi = method.getDeclaringClass().getAnnotation(RouteApi.class);
        String basePackage = routeApi == null ? "" : routeApi.basePackage();

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
                Object value = formatValue(defaultValue, defaultExtra.type());
                addExtra(bundle, name, value);
                //
                routeMeta.addParam(name, value);
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
                if (parameterTypes[i] == OnActivityResult.class &&
                        routeMeta.getRouteType() == RouteType.ACTIVITY
                        && routeActivity != null && routeActivity.requestCode() > 0
                        && objects[i] != null) {
                    RouteManager.setOnActivityResultCallback(routeActivity.requestCode(), (OnActivityResult) objects[i]);
                    continue;
                }

                Annotation[] annotations = parameterAnnotations[i];
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Extra) {
                        //向intent中添加一组值
                        String name = ((Extra) annotation).value();
                        Object value = objects[i];
                        addExtra(bundle, name, value);
                        routeMeta.addParam(name, value);
                        continue foreach_param;
                    } else if (annotation instanceof Data) {
                        if (parameterTypes[i] == Uri.class) {
                            routeMeta.setData((Uri) objects[i]);
                        }
                        continue foreach_param;
                    }
                }

                if (parameterTypes[i] == Uri.class) {
                    routeMeta.setData((Uri) objects[i]);
                }
            }
        }

        switch (routeMeta.getRouteType()) {
            case RouteType.ACTIVITY:

                Log.i(TAG, bundle.toString());

                // Build intent
                intent.putExtras(bundle);

                //要跳转的Activity
                Class<?> destination = routeActivity.value();
                if (destination == void.class && !routeActivity.destination().isEmpty()) {
                    String destinationName = routeActivity.destination();
                    if (destinationName.startsWith(".")) {
                        destinationName = basePackage + destinationName;
                    }
                    destination = Class.forName(destinationName);
                }
                routeMeta.setDestination(destination);
                //要跳转的action
                routeMeta.setAction(routeActivity.action());
                //请求的requestCode
                routeMeta.setRequestCode(routeActivity.requestCode());
                //
                routeMeta.setCategory(routeActivity.category());
                //
                routeMeta.setFlags(routeActivity.flags());

                routeMeta.setType(routeActivity.type());

                final int enterAnim = routeActivity.enterAnim();
                final int exitAnim = routeActivity.exitAnim();

                if (destination != void.class) {
                    intent.setClass(currentContext, destination);
                }
                if (!TextUtils.isEmpty(routeMeta.getAction())) {
                    intent.setAction(routeMeta.getAction());
                }
                for (String category : routeMeta.getCategory()) {
                    intent.addCategory(category);
                }

                if (-1 != routeMeta.getFlags()) {
                    intent.setFlags(routeMeta.getFlags());
                } else if (!(currentContext instanceof Activity)) {    // Non activity, need less one flag.
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }

                if (!TextUtils.isEmpty(routeMeta.getType())) {
                    if (routeMeta.getData() != null) {
                        intent.setDataAndType(routeMeta.getData(), routeMeta.getType());
                    } else {
                        intent.setType(routeMeta.getType());
                    }
                }

                //如果返回类型为intent时，直接返回该值
                if (method.getReturnType() == Intent.class) {
                    return intent;
                }

                //存在拦截则返回
                if (RouteManager.doIntercept(routeMeta)) {
                    return null;
                }
                // Navigation in main looper.
                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {

                        if (routeMeta.getRequestCode() > 0) {  // Need start for result
                            ActivityCompat.startActivityForResult((Activity) currentContext, intent, routeMeta.getRequestCode(), null);
                        } else {
                            ActivityCompat.startActivity(currentContext, intent, null);
                        }

                        if ((0 != enterAnim || 0 != exitAnim) && currentContext instanceof Activity) {    // Old version.
                            ((Activity) currentContext).overridePendingTransition(enterAnim, exitAnim);
                        }
                    }
                });

                break;
            case RouteType.FRAGMENT:
                if (method.getReturnType() == void.class) {
                    return null;
                }
                destination = routeFragment.value();
                if (destination == void.class && !routeActivity.destination().isEmpty()) {
                    String destinationName = routeActivity.destination();
                    if (destinationName.startsWith(".")) {
                        destinationName = basePackage + destinationName;
                    }
                    destination = Class.forName(destinationName);
                }
                routeMeta.setDestination(destination);

                //存在拦截则返回
                if (RouteManager.doIntercept(routeMeta)) {
                    return null;
                }

                if (destination != void.class) {
                    try {
                        Object instance = destination.getConstructor().newInstance();
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
            case RouteType.SERVICE:
                // Build intent
                intent.putExtras(bundle);

                //要跳转的Activity
                destination = routeService.value();
                if (destination == void.class && !routeActivity.destination().isEmpty()) {
                    String destinationName = routeActivity.destination();
                    if (destinationName.startsWith(".")) {
                        destinationName = basePackage + destinationName;
                    }
                    destination = Class.forName(destinationName);
                }

                routeMeta.setDestination(destination);
                //要跳转的action
                routeMeta.setAction(routeService.action());

                if (destination != void.class) {
                    intent.setClass(currentContext, destination);
                }
                if (!TextUtils.isEmpty(routeMeta.getAction())) {
                    intent.setAction(routeMeta.getAction());
                }

                //存在拦截则返回
                if (RouteManager.doIntercept(routeMeta)) {
                    return null;
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
