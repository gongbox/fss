package com.gongbo.fss.demo.route.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

import com.gongbo.fss.demo.util.ToastUtils
import com.gongbo.fss.router.annotation.Route

@Route
class RouteTestService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        ToastUtils.showToast("route service")

        val value = intent.getStringExtra("value")
        ToastUtils.showToast("value:" + value!!)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        ToastUtils.showToast("route service")
        super.onCreate()
    }
}
