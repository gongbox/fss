package com.fss.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
@Repeatable(value = com.fss.router.annotation.Routes.class)
public @interface Route {
    String destination() default "";

    String className() default "";

    String group() default "";

    String name() default "";

    //
    String action() default "";

    int requestCode() default 0;

    String[] category() default {};

    int flags() default 0;

    boolean navigation() default true;

    boolean withResultCallBack() default false;

    com.fss.router.annotation.RouteExtra[] routeExtras() default {};

    DefaultExtra[] defaultExtras() default {};

    int enterAnim() default 0;

    int exitAnim() default 0;

    String desc() default "";
}
