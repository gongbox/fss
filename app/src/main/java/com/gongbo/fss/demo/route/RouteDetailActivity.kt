package com.gongbo.fss.demo.route

import android.content.Intent

import com.gongbo.fss.base.BaseFssActivity
import com.gongbo.fss.bind.annotation.BindActivity
import com.gongbo.fss.bind.annotation.BindExtra
import com.gongbo.fss.demo.R
import com.gongbo.fss.router.annotation.DefaultExtra
import com.gongbo.fss.router.annotation.Route
import com.gongbo.fss.router.annotation.RouteExtra

import com.gongbo.fss.demo.util.ToastUtils.showToast
import com.gongbo.fss.router.annotation.Routes

//普通路由
//@Route
//使用Action方式路由
@Routes(
    value = [
        //使用分组
        Route(action = "com.gongbo.fss.route.detail"),
        //设置分组
        Route(name = "navigateToRouteDetailActivity", group = "detail", requestCode = 1234, category = [Intent.CATEGORY_DEFAULT], routeExtras = [RouteExtra(name = "value", type = String::class)], defaultExtras = [DefaultExtra(name = "defaultValue", defaultValue = "hello")], enterAnim = android.R.anim.slide_in_left, desc = "示例")//设置路由名称
    ]
)
//添加requestCode
//设置category
//设置falgs
//        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK,
//添加参数
//添加默认参数
//activity进入动画
//添加注释
@BindActivity(layout = R.layout.activity_route_detail, finish = [R.id.img_back])
class RouteDetailActivity : BaseFssActivity() {

    @BindExtra(name = "value")
    private val value: String? = null

    @BindExtra
    private val defaultValue: String? = null

    override fun initData() {
        value?.let { showToast(it) }
        defaultValue?.let { showToast(it) }
    }
}
