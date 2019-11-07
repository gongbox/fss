package com.gongbo.fss.demo.route.api;

import android.content.Context;

import com.gongbo.fss.demo.route.RouteDetailActivity;
import com.gongbo.fss.demo.route.service.RouteTestService;
import com.gongbo.fss.router.annotation.Extra;
import com.gongbo.fss.router.annotation.RouteActivity;
import com.gongbo.fss.router.annotation.RouteApi;
import com.gongbo.fss.router.annotation.RouteService;

/**
 * 自定义路由路由API
 */
@RouteApi(name = "test")   //自定义的路由API必须添加@RouteApi注解
public interface ITestRouteApi {
    //路由到一个Service
    @RouteService(RouteTestService.class)
    void navigateToRouteTestService(Context context, @Extra(value = "value") String value);

    //路由到一个Activity
    @RouteActivity(RouteDetailActivity.class)
    void navigateToRouteDetailActivity(Context context);
}
