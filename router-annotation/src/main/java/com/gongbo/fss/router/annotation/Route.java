package com.gongbo.fss.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
@Repeatable(value = Routes.class)
public @interface Route {

    String group() default "";

    String name() default "";

    String action() default "";

    int requestCode() default 0;

    String[] category() default {};

    int[] flags() default {};

    boolean navigation() default true;

    boolean withResultCallBack() default false;

    RouteExtra[] routeExtras() default {};

    DefaultExtra[] defaultExtras() default {};

    String desc() default "";
}
