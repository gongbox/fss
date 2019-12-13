package com.fss.router.api.interceptor;

import com.fss.router.api.model.RouteMeta;

public interface IInterceptor {
    boolean intercept(RouteMeta routeMeta);
}
