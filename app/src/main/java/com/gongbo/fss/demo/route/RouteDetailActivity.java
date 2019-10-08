package com.gongbo.fss.demo.route;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.router.annotation.Route;

@Route
@BindActivity(layout = R.layout.activity_route_detail,finish = R.id.btn_finish)
public class RouteDetailActivity extends BaseFssActivity {

}
