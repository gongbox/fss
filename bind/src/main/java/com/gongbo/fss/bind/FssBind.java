package com.gongbo.fss.bind;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindBindingParam;
import com.gongbo.fss.bind.annotation.BindFragment;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.bind.annotation.BindParam;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.common.common;
import com.gongbo.fss.common.kotlin.Pair;
import com.gongbo.fss.common.stream.Stream;
import com.gongbo.fss.common.util.ReflectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.gongbo.fss.common.kotlin.Pair.pairOf;


public final class FssBind {

    /**
     * 绑定
     *
     * @param obj
     * @param clazz
     */
    public static void bind(@NonNull Object obj, Class clazz) {
        long beforeTime = System.currentTimeMillis();
        //绑定路由
        BindRouteProcessor.bindRoute(obj);

        //绑定finish
        BindActivityProcessor.bindFinish(obj);

        //绑定视图或参数
        List<Field> fields = getBindFields(obj, clazz);
        for (Field field : fields) {
            //绑定视图
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView != null) {
                BindViewProcessor.bindView(obj, field, bindView);
                continue;
            }
            //绑定参数
            BindBindingParam bindBindingParam = field.getAnnotation(BindBindingParam.class);
            if (bindBindingParam != null) {
                BindBindingParamProcessor.bindBindingParam(obj, field, bindBindingParam);
                continue;
            }
            //绑定参数
            BindParam bindParam = field.getAnnotation(BindParam.class);
            if (bindParam != null) {
                BindParamProcessor.bindParam(obj, field, bindParam);
            }
        }

        //绑定点击事件
        BindOnClickProcessor.bindOnClick(obj);

        //绑定点击事件
        List<Pair<Method, List<BindOnClick>>> bindOnClickMethods = getBindOnClickMethods(obj.getClass(), clazz);
        for (Pair<Method, List<BindOnClick>> pair : bindOnClickMethods) {
            BindOnClickProcessor.bindOnClick(obj, pair.first, pair.second);
        }

        //绑定参数
        BindBindingParamProcessor.bindBindingParam(obj);
        long time = System.currentTimeMillis() - beforeTime;
        Log.i("Bind", time + "ms");
    }

    /**
     * 绑定view
     *
     * @param obj
     * @param view
     */
    public static void bindView(Object obj, Class clazz, View view) {
        List<Field> fields = getBindViewFields(obj, clazz);
        for (Field field : fields) {
            //初始化绑定View对象
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView != null) {
                BindViewProcessor.bindView(obj, view, field, bindView);
            }
        }
    }

    /**
     * 获取layout
     *
     * @param obj
     * @return
     */
    public static int getLayoutId(Object obj) {
        BindActivity bindActivity = obj.getClass().getAnnotation(BindActivity.class);
        if (bindActivity != null) {
            return bindActivity.layout();
        }
        BindFragment bindFragment = obj.getClass().getAnnotation(BindFragment.class);
        if (bindFragment != null) {
            return bindFragment.layout();
        }
        throw new RuntimeException(obj.getClass().getCanonicalName() + "类没有声明BindActivity或BindFragment注解");
    }

    /**
     * 获取绑定字段
     *
     * @param obj
     * @param untilClass
     * @return
     */
    private static List<Field> getBindFields(@NonNull Object obj, Class untilClass) {
        Stream<Field> bindFields = new Stream<Field>();
        Class clazz = obj.getClass();
        while (clazz != null && clazz != untilClass) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field fieldItem : fields) {
                for (Annotation annotation : fieldItem.getDeclaredAnnotations()) {
                    Class annotationClass = annotation.annotationType();
                    //包含指定注解的添加
                    if (annotationClass == BindView.class || annotationClass == BindParam.class
                            || annotationClass == BindBindingParam.class) {
                        //如果集合里没有该字段，则添加
                        if (!bindFields.any(field -> ReflectUtils.compare(fieldItem, field))) {
                            bindFields.add(fieldItem);
                            break;
                        }
                    }
                }
            }
            //
            clazz = clazz.getSuperclass();
        }
        return bindFields.toList();
    }

    /**
     * 获取绑定字段
     *
     * @param obj
     * @param untilClass
     * @return
     */
    private static List<Field> getBindViewFields(@NonNull Object obj, Class untilClass) {
        Stream<Field> bindFields = new Stream<Field>();
        Class clazz = obj.getClass();
        while (clazz != null && clazz != untilClass) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field fieldItem : fields) {
                for (Annotation annotation : fieldItem.getDeclaredAnnotations()) {
                    Class annotationClass = annotation.annotationType();
                    //包含指定注解的添加
                    if (annotationClass == BindView.class) {
                        //如果集合里没有该字段，则添加
                        if (!bindFields.any(field -> ReflectUtils.compare(fieldItem, field))) {
                            bindFields.add(fieldItem);
                            break;
                        }
                    }
                }
            }
            //
            clazz = clazz.getSuperclass();
        }
        return bindFields.toList();
    }

    /**
     * 获取绑定方法
     *
     * @param
     * @return
     */
    private static List<Pair<Method, List<BindOnClick>>> getBindOnClickMethods(@NonNull Class clazz, Class untilClass) {
        List<Pair<Method, List<BindOnClick>>> methods = new ArrayList<>();
        while (clazz != null && clazz != untilClass) {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method methodItem : declaredMethods) {
                for (Annotation annotation : methodItem.getDeclaredAnnotations()) {
                    //包含指定注解的添加
                    if (annotation.annotationType() == BindOnClick.class) {
                        BindOnClick bindOnClick = (BindOnClick) annotation;
                        Pair<Method, List<BindOnClick>> pairMethod = common.find(methods, pair -> ReflectUtils.compare(pair.first, methodItem));
                        if (pairMethod != null) {
                            pairMethod.second.add(bindOnClick);
                        } else {
                            List<BindOnClick> bindOnClicks = new ArrayList<>();
                            bindOnClicks.add(bindOnClick);
                            methods.add(pairOf(methodItem, bindOnClicks));
                        }
                        break;
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        return methods;
    }

}
