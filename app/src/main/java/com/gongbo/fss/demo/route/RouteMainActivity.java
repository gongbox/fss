package com.gongbo.fss.demo.route;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.router.annotation.Route;

import static com.gongbo.fss.router.FssRouteApi.DetailRouteApi;
import static com.gongbo.fss.router.FssRouteApi.TestRouteApi;

@Route
@BindActivity(value = R.layout.activity_route_main, finishViewId = R.id.img_back)
public class RouteMainActivity extends BaseFssActivity {

    @BindOnClick(R.id.btn_to_detail)
    private void gotoDetail() {
        DetailRouteApi.navigateToRouteDetailActivity(this, "1234");
    }

    @BindOnClick(R.id.btn_to_service)
    private void gotoService() {
        TestRouteApi.navigateToRouteTestService(this, "12465");
    }
}
