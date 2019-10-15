package com.gongbo.fss.demo.route;

import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.router.annotation.Route;

import static com.gongbo.fss.router.FssRouteApi.DefaultRouteApi;
import static com.gongbo.fss.router.FssRouteApi.MainRouteApi;

@Route
@BindActivity(layout = R.layout.activity_route_main, finish = R.id.img_back)
public class RouteMainActivity extends BaseActivity {

    @BindOnClick(id = R.id.btn_to_detail)
    private void gotoDetail() {
        MainRouteApi.navigateToRouteDetailActivity(this);
    }

    @BindOnClick(id = R.id.btn_to_service)
    private void gotoService() {
        DefaultRouteApi.navigateToRouteTestService(this);
    }
}
