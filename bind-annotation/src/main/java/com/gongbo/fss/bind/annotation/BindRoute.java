package com.gongbo.fss.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Repeatable(BindRoutes.class)
public @interface BindRoute {
    int id();//id为View的Id集合

    Class toActivity() default Object.class; //要跳转的Activity

    String action() default "";

    String[] category() default {};

    int flags() default 0;

    int requestCode() default -1;    //请求码

    String[] extras() default {};  //路由参数

    int enterAnim() default 0;

    int exitAnim() default 0;
}
