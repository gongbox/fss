package com.gongbo.fss.demo;

import android.app.Application;

import com.gongbo.fss.demo.route.RouteDetailActivity;
import com.gongbo.fss.demo.util.ToastUtils;
import com.gongbo.fss.router.annotation.Route;

@Route(
        destination = "com.gongbo.fss.demo.route.RouteDetailActivity",
        name = "routeTestDestination"
)
public class FssApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.register(getApplicationContext());
    }

}
