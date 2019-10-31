package com.gongbo.fss.runpriority.model;


import com.gongbo.fss.runpriority.constant.Priority;

import java.lang.reflect.Method;

public class CallMethodInfo {
    public String name;

    public Method method;

    public Object[] paramValues;

    public boolean required;

    public int priority = Priority.NORMAL;

    public CallMethodInfo(String name, Object... paramValues) {
        this(name, false, paramValues);
    }

    public CallMethodInfo(String name, boolean required, Object[] paramValues) {
        this.name = name;
        this.required = required;
        this.paramValues = paramValues;
    }
}
