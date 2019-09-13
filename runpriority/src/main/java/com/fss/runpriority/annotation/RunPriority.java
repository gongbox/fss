package com.fss.runpriority.annotation;


import com.fss.runpriority.constant.Priority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//运行优先级注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RunPriority {
    //优先级
    int value() default Priority.NORMAL;
}
