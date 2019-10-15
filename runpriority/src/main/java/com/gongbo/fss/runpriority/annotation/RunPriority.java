package com.gongbo.fss.runpriority.annotation;


import com.gongbo.fss.runpriority.enums.Priority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//运行优先级注解，定义在initView,initListener,initData等方法上
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RunPriority {
    int priority() default Priority.NORMAL;
}
