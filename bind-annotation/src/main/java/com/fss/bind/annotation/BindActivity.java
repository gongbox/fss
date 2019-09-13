package com.fss.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface BindActivity {

    //对应的布局id
    int value();

    //触发finish方法的View的id
    int[] finishViewId() default {};
}