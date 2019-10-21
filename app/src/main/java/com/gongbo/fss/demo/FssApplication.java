package com.gongbo.fss.demo;

import android.app.Application;

import com.gongbo.fss.demo.util.ToastUtils;

public class FssApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.register(getApplicationContext());
    }

}
