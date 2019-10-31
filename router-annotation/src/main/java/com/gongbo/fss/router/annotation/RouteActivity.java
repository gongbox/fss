package com.gongbo.fss.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RouteActivity {
    //要跳转的Activity
    Class<?> value() default Object.class;

    String action() default "";

    int requestCode() default 0;

    String[] category() default {}; //

    int flags() default 0;       //

    int enterAnim() default 0;

    int exitAnim() default 0;

}
