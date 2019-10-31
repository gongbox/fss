package com.gongbo.fss.runpriority.exception;

public class NotFoundMethodException extends RuntimeException {

    public NotFoundMethodException(String name, Class[] paramTypes) {
        super(toMethodString(name, paramTypes));
    }

    private static String toMethodString(String name, Class[] paramTypes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (paramTypes != null) {
            for (int i = 0; i < paramTypes.length; i++) {
                stringBuilder.append(paramTypes[i].getCanonicalName());
                if (i < paramTypes.length - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        String params = stringBuilder.toString();
        return "can't find method:" + name + "(" + params + ")";
    }

}
