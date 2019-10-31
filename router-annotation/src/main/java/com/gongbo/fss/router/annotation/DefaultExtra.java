package com.gongbo.fss.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Repeatable(value = DefaultExtras.class)
public @interface DefaultExtra {
    //
    String name();

    String defaultValue();

    Class<?> type() default String.class;
}
