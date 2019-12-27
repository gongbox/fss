package com.fss.demo.route.api;

import android.content.Context;

import com.fss.router.annotation.RouteActivity;
import com.fss.router.annotation.RouteApi;
import com.fss.router.annotation.RouteService;


@RouteApi(value = "destination2", basePackage = "com.fss.demo.route")
public interface IDestinationRouteApi {
    /**
     * 路由到其他模块的Activity，请保证存在再进行路由
     */
    @RouteActivity(
            destination = "com.fss.demo.OtherModuleActivity"
    )
    void navigateToOtherModuleActivity(Context context);

    /**
     * 路由到其他模块的Service，请保证存在再进行路由
     */
    @RouteService(
            destination = "com.fss.demo.OtherModuleService"
    )
    void navigateToOtherModuleService(Context context);

    @RouteActivity(destination = ".RouteDestinationActivity")
    void naviagetToRouteDestinationActivity(Context context);
}
