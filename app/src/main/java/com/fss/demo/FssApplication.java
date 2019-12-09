package com.fss.demo;

import android.app.Application;

import com.fss.demo.util.ToastUtils;
import com.fss.router.annotation.Route;

public class FssApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.register(getApplicationContext());
    }

}
