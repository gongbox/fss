package com.gongbo.fss.demo.route;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.router.FssRouteApi;
import com.gongbo.fss.router.annotation.Route;

@Route
@BindActivity(layout = R.layout.activity_route_main)
public class RouteMainActivity extends BaseFssActivity {

    @BindOnClick(id = R.id.btn_to_detail)
    private void gotoDetail() {
        FssRouteApi.DefaultRouteApi.routeToRouteDetailActivity(this);
    }

    @BindOnClick(id = R.id.btn_to_service)
    private void gotoService() {
        FssRouteApi.DefaultRouteApi.routeToRouteTestService(this);
    }
}