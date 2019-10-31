package com.gongbo.fss.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface BindView {
    //view的id值
    int value();

    //是否必须存在该view
    boolean required() default true;
}