package com.gongbo.fss.runpriority;


import com.gongbo.fss.common.util.ReflectUtils;
import com.gongbo.fss.runpriority.annotation.RunPriority;
import com.gongbo.fss.runpriority.model.CallMethodInfo;
import com.gongbo.fss.runpriority.model.RunPriorityInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
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
                if (method == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < paramTypes.length; i++) {
                        stringBuilder.append(paramTypes[i].getCanonicalName());
                        if (i < paramTypes.length - 1) {
                            stringBuilder.append(",");
                        }
                    }
                    String params = stringBuilder.toString();
                    throw new RuntimeException("can't find method:" + callMethodInfo.name + "(" + params + ")");
                }
            } else {
                method = ReflectUtils.getMethod(obj.getClass(), callMethodInfo.name);
                if (method == null) {
                    throw new RuntimeException("can't find method:" + callMethodInfo.name + "()");
                }
            }

            RunPriority runPriority = method.getAnnotation(RunPriority.class);
            if (runPriority != null) {
                callMethodInfo.priority = runPriority.priority();
            }
            callMethodInfo.method = method;
        }

        //对方法按优先级排序
        Collections.sort(callMethodInfos, (o1, o2) -> Integer.compare(o1.priority, o2.priority));

        //调用方法
        for (CallMethodInfo callMethodInfo : callMethodInfos) {
            Method method = callMethodInfo.method;
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            try {
                //调用方法
                method.invoke(obj, callMethodInfo.paramValues);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
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
