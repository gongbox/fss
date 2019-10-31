package com.gongbo.fss.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RouteService {

    //路由的service的类
    Class<?> value() default Object.class;

    //以action方式路由时，需要设置action
    String action() default "";

}
