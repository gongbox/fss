package com.fss.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Autowired {
    //对应的key值，为空则取字段名作为key值
    String value() default "";

    //是否必须存在该key的值，否则抛出异常
    boolean required() default false;
}
