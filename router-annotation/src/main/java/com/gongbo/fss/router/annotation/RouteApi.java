package com.gongbo.fss.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface RouteApi {
    //在FssRouteApi类中对应的常量名，默认为该接口的名字，如果该接口首字母为I，则去掉I
    String name() default "";
}
