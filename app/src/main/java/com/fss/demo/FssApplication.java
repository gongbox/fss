package com.fss.demo;

import android.app.Application;

import com.fss.demo.util.ToastUtils;
import com.fss.router.api.launcher.FssRouter;

public class FssApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.register(getApplicationContext());

        FssRouter.getInstance().addInterceptor(routeMeta -> {
            if ("com.fss.route.action".equals(routeMeta.getAction())) {
                ToastUtils.showToast("路由被拦截");
                return true;
            }
            return false;
        });
    }

}
