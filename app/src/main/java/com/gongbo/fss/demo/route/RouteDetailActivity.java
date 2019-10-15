package com.gongbo.fss.demo.route;

import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindExtra;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.router.annotation.Route;
import com.gongbo.fss.router.annotation.RouteExtra;

//@Route
@Route(action = "com.gongbo.fss.route.detail")
@Route(routeExtras = {
        @RouteExtra(name = "value", type = String.class)
})
@BindActivity(layout = R.layout.activity_route_detail, finish = R.id.img_back)
public class RouteDetailActivity extends BaseActivity {

    @BindExtra(name = "value")
    private String value;

    @Override
    protected void initData() {
        showToast(value);
    }
}
