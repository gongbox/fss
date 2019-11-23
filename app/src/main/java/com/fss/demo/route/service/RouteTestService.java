package com.fss.demo.route.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


import com.fss.demo.util.ToastUtils;
import com.fss.router.annotation.Route;

@Route
public class RouteTestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ToastUtils.showToast("route service");

        String value = intent.getStringExtra("value");
        ToastUtils.showToast("value:" + value);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        ToastUtils.showToast("route service");
        super.onCreate();
    }
}
