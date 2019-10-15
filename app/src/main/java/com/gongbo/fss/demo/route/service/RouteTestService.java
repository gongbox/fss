package com.gongbo.fss.demo.route.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.gongbo.fss.router.annotation.Route;

@Route
public class RouteTestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "route service", Toast.LENGTH_SHORT).show();

        String value = intent.getStringExtra("value");
        Toast.makeText(getApplicationContext(), "value:" + value, Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Toast.makeText(getApplicationContext(), "route service", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }
}
