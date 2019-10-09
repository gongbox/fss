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

    Class toActivity(); //要跳转的Activity

    int requestCode() default -1;    //请求码

    String[] extraFields() default {};  //路由参数
}
