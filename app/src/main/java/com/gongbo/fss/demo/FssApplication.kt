package com.gongbo.fss.demo

import android.app.Application

import com.gongbo.fss.demo.util.ToastUtils

class FssApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ToastUtils.register(applicationContext)
    }

}
