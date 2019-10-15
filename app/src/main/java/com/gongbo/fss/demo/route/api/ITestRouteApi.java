package com.gongbo.fss.demo.route.api;

import android.content.Context;

import com.gongbo.fss.demo.route.service.RouteTestService;
import com.gongbo.fss.router.annotation.Extra;
import com.gongbo.fss.router.annotation.RouteApi;
import com.gongbo.fss.router.annotation.RouteService;

@RouteApi
public interface ITestRouteApi {
    @RouteService(
            clazz = RouteTestService.class
    )
    void navigateToRouteTestService(Context context, @Extra(name = "value") String value);
}
