package com.gongbo.fss.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Repeatable(BindOnClicks.class)
@Inherited  //该注解可继承，对类可继承，其他不能继承
public @interface BindOnClick {
    //view的id集合
    int[] value();

    //点击事件的方法,只有注解在类上时有效
    String onClickMethod() default "";
}
