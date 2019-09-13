package com.fss.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindParam {

    //该参数的key值
    String value() default "";

    //是否必须存在该key的值，否则抛出异常
    boolean required() default false;
}