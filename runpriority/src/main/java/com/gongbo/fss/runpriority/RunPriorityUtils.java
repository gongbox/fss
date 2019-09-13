package com.gongbo.fss.runpriority;


import com.gongbo.fss.common.function.Optional;
import com.gongbo.fss.common.util.ReflectUtils;
import com.gongbo.fss.runpriority.annotation.RunPriority;

import static com.gongbo.fss.common.kotlin.Pair.pairOf;
import static com.gongbo.fss.common.stream.Stream.streamOf;

public class RunPriorityUtils {

    public static void callAll(Object obj, String... methodNames) {
        streamOf(methodNames)
                //根据方法名称寻找方法列表
                .map(methodName -> ReflectUtils.getMethod(obj.getClass(), methodName))
                .filterNull()
                //转换便于后续处理
                .map(method -> pairOf(method, Optional.ofNullable(method.getAnnotation(RunPriority.class)).map(RunPriority::priority).orElse(Priority.NORMAL)))
                //根据优先级排序
                .sort((pair1, pair2) -> pair1.second.compareTo(pair2.second))
                //根据排序后的顺序执行方法
                .forEach(item -> ReflectUtils.methodInvoke(obj, item.first));
    }

}
