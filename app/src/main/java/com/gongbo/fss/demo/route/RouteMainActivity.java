package com.gongbo.fss.demo.route;

import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.router.FssRouteApi;
import com.gongbo.fss.router.annotation.Route;

@Route
@BindActivity(value = R.layout.activity_route_main, finishViewId = R.id.img_back)
public class RouteMainActivity extends BaseActivity {

    @BindOnClick(R.id.btn_to_detail)
    private void gotoDetail() {
        FssRouteApi.DETAIL.navigateToRouteDetailActivity(this, "1234");
    }

    @BindOnClick(R.id.btn_to_service)
    private void gotoService() {
        FssRouteApi.TEST.navigateToRouteTestService(this, "12465");
    }
}
