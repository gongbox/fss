package com.gongbo.fss.router.api.util;

public class Common {

    public static Object notnull(Object... objs) {
        for (Object obj : objs) {
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }
}
