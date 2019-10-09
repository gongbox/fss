package com.gongbo.fss.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindViewModel {
    boolean bActivity() default false;//当在fragment中使用时，且fragment所在的Activity为FragmentActivity时，bGetActivity=true表示获取FragmentActivity的ViewModel
}
