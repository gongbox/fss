package com.gongbo.fss.demo.route

import com.gongbo.fss.base.BaseFssActivity
import com.gongbo.fss.bind.annotation.BindActivity
import com.gongbo.fss.bind.annotation.BindOnClick
import com.gongbo.fss.demo.R
import com.gongbo.fss.router.annotation.Route

import com.gongbo.fss.router.FssRouteApi.DetailRouteApi
import com.gongbo.fss.router.FssRouteApi.TestRouteApi

@Route
@BindActivity(layout = R.layout.activity_route_main, finish = [R.id.img_back])
class RouteMainActivity : BaseFssActivity() {

    @BindOnClick(id = [R.id.btn_to_detail])
    private fun gotoDetail() {
        DetailRouteApi.navigateToRouteDetailActivity(this, "1234")
    }

    @BindOnClick(id = [R.id.btn_to_service])
    private fun gotoService() {
        TestRouteApi.navigateToRouteTestService(this, "12465")
    }
}
