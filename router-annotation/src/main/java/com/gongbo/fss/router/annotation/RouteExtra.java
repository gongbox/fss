package com.gongbo.fss.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface RouteExtra {
    //参数的key值
    String name();

    //参数名，默认为参数的key值
    String paramName() default "";

    //参数的类型
    Class<?> type() default Object.class;

    //参数的注释
    String desc() default "";
}