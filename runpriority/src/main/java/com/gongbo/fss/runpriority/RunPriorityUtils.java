package com.gongbo.fss.runpriority;


import com.gongbo.fss.common.exception.ExecuteException;
import com.gongbo.fss.common.util.ReflectUtils;
import com.gongbo.fss.runpriority.annotation.RunPriority;
import com.gongbo.fss.runpriority.exception.NotFoundMethodException;
import com.gongbo.fss.runpriority.model.CallMethodInfo;
import com.gongbo.fss.runpriority.model.RunPriorityInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RunPriorityUtils {

    /**
     * 根据runPriorityInfo参数调用方法
     *
     * @param runPriorityInfo 方法信息
     */
    public static void call(RunPriorityInfo runPriorityInfo) {
        List<CallMethodInfo> callMethodInfos = runPriorityInfo.getCallMethodInfos();
        if (callMethodInfos == null || callMethodInfos.isEmpty()) {
            return;
        }
        Object obj = runPriorityInfo.getObject();

        for (CallMethodInfo callMethodInfo : callMethodInfos) {
            Method method;
            if (callMethodInfo.paramValues != null && callMethodInfo.paramValues.length > 0) {
                Class[] paramTypes = new Class[callMethodInfo.paramValues.length];
                for (int i = 0; i < callMethodInfo.paramValues.length; i++) {
                    paramTypes[i] = callMethodInfo.paramValues[i].getClass();
                }
                method = ReflectUtils.getMethod(obj.getClass(), callMethodInfo.name, paramTypes);
                if (method == null && callMethodInfo.required) {
                    throw new NotFoundMethodException(callMethodInfo.name, paramTypes);
                }
            } else {
                method = ReflectUtils.getMethod(obj.getClass(), callMethodInfo.name);
                if (method == null && callMethodInfo.required) {
                    throw new NotFoundMethodException(callMethodInfo.name, null);
                }
            }

            if (method != null) {
                RunPriority runPriority = method.getAnnotation(RunPriority.class);
                if (runPriority != null) {
                    callMethodInfo.priority = runPriority.value();
                }
                callMethodInfo.method = method;
            }
        }

        //对方法按优先级排序
        Collections.sort(callMethodInfos, new Comparator<CallMethodInfo>() {
            @Override
            public int compare(CallMethodInfo o1, CallMethodInfo o2) {
                return (o1.priority < o2.priority) ? -1 : ((o1.priority == o2.priority) ? 0 : 1);
            }
        });

        //调用方法
        for (CallMethodInfo callMethodInfo : callMethodInfos) {
            Method method = callMethodInfo.method;
            if (method == null) {
                continue;
            }
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            try {
                //调用方法
                method.invoke(obj, callMethodInfo.paramValues);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                throw new ExecuteException(e.getCause());
            }
        }
    }

    /**
     * 调用所有方法（方法都为无参方法时）
     *
     * @param obj         当前对象
     * @param methodNames 要调用的方法集
     */
    public static void call(Object obj, String... methodNames) {
        RunPriorityInfo runPriorityInfo = new RunPriorityInfo.Builder(obj)
                .addMethods(methodNames)
                .build();

        call(runPriorityInfo);
    }

}
