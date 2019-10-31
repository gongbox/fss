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
    //触发路由的view的id
    int viewId();

    //要去的Activity
    Class toActivity() default Object.class;

    String action() default "";

    String[] category() default {};

    int flags() default 0;

    //请求码
    int requestCode() default -1;

    //路由参数
    String[] extras() default {};

    //进入动画
    int enterAnim() default 0;

    //退出时的动画
    int exitAnim() default 0;
}
