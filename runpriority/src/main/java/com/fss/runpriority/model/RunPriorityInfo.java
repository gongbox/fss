package com.fss.runpriority.model;

import java.util.LinkedList;
import java.util.List;

public class RunPriorityInfo {

    private Object object;

    private List<CallMethodInfo> callMethodInfos = new LinkedList<>();

    public RunPriorityInfo() {
    }

    public RunPriorityInfo(Object object, List<CallMethodInfo> callMethodInfos) {
        this.object = object;
        this.callMethodInfos = callMethodInfos;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<CallMethodInfo> getCallMethodInfos() {
        return callMethodInfos;
    }

    public void setCallMethodInfos(List<CallMethodInfo> callMethodInfos) {
        this.callMethodInfos = callMethodInfos;
    }

    public static class Builder {

        private Object object;

        private List<CallMethodInfo> methodInfos = new LinkedList<>();

        public Builder(Object object) {
            this.object = object;
        }

        private Builder addMethod(CallMethodInfo methodInfo) {
            if (methodInfo != null && methodInfo.name != null && !methodInfo.name.isEmpty()) {
                methodInfos.add(methodInfo);
            }
            return this;
        }

        public Builder addMethods(String... methodNames) {
            for (String methodName : methodNames) {
                addMethod(new CallMethodInfo(methodName));
            }
            return this;
        }

        public Builder addMethod(String methodName, Object... params) {
            addMethod(new CallMethodInfo(methodName, params));
            return this;
        }

        public Builder addRequiredMethods(String... methodNames) {
            for (String methodName : methodNames) {
                addMethod(new CallMethodInfo(methodName, true));
            }
            return this;
        }

        public Builder addRequiredMethod(String methodName, Object... params) {
            addMethod(new CallMethodInfo(methodName, true, params));
            return this;
        }

        public RunPriorityInfo build() {
            return new RunPriorityInfo(object, methodInfos);
        }
    }
}
