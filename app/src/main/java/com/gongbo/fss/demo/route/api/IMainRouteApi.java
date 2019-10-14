package com.gongbo.fss.demo.route.api;

import android.content.Context;

import com.gongbo.fss.demo.route.RouteDetailActivity;
import com.gongbo.fss.router.annotation.RouteActivity;
import com.gongbo.fss.router.annotation.RouteApi;

@RouteApi
public interface IMainRouteApi {
    @RouteActivity(
            clazz = RouteDetailActivity.class
    )
    void navigateToRouteDetailActivity(Context packageContext);
}
