package com.fss.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by $USER_NAME on 2019/1/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Repeatable(BindExtras.class)
public @interface BindExtra {
    //该参数的key值
    String value() default "";

    //databinding生成的id
    int bindingId() default -1;

    //databinding对应的变量名,是bindingId的替代
    String bindingName() default "";

    //当前对象的databinding变量名
    String bindingFieldName() default "binding";

    //是否必须存在该key的值，否则抛出异常
    boolean required() default false;
}
