package com.fss.demo.route.api;

import android.content.Context;
import android.content.Intent;

import com.fss.demo.route.RouteDetailActivity;
import com.fss.demo.route.service.RouteTestService;
import com.fss.router.annotation.Extra;
import com.fss.router.annotation.RouteActivity;
import com.fss.router.annotation.RouteApi;
import com.fss.router.annotation.RouteService;

/**
 * 自定义路由路由API
 */
@RouteApi("test")   //自定义的路由API必须添加@RouteApi注解
public interface ITestRouteApi {
    //路由到一个Service
    @RouteService(RouteTestService.class)
    void navigateToRouteTestService(Context context, @Extra(value = "value") String value);

    //路由到一个Activity
    @RouteActivity(RouteDetailActivity.class)
    void navigateToRouteDetailActivity(Context context);

    @RouteActivity(value = RouteDetailActivity.class)
    Intent buildRouteDetailActivityIntent(Context context);
}
