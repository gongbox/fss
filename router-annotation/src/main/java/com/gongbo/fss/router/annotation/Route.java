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
    //路由组
    String group() default "";

    //路由方法名
    String name() default "";

    //
    String action() default "";

    int requestCode() default 0;

    String[] category() default {};

    int[] flags() default {};

    //是否跳转，为true时跳转，为false时返回构建的intent对象
    boolean navigation() default true;

    //是否
    boolean withResultCallBack() default false;

    //路由参数集
    RouteExtra[] routeExtras() default {};

    //默认参数集
    DefaultExtra[] defaultExtras() default {};

    //进入动画
    int enterAnim() default 0;

    //退出动画
    int exitAnim() default 0;

    //添加的注释
    String desc() default "";
}
