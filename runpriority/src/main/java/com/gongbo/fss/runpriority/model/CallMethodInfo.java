package com.gongbo.fss.runpriority.model;

import com.gongbo.fss.runpriority.Priority;

import java.lang.reflect.Method;

public class CallMethodInfo {
    public String name;

    public Method method;

    public Object[] paramValues;

    public int priority = Priority.NORMAL;

    public CallMethodInfo(String name, Object... paramValues) {
        this.name = name;
        this.paramValues = paramValues;
    }

}
