package com.fss.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Repeatable(BindRoutes.class)
public @interface BindRoute {
    //触发路由的view的id
    int viewId();

    //要去的Activity
    Class toActivity() default void.class;

    //要路由的目标类
    String destination() default "";

    String action() default "";

    String[] category() default {};

    int flags() default 0;

    int requestCode() default -1;

    String[] extras() default {};

    int enterAnim() default 0;

    int exitAnim() default 0;
}
