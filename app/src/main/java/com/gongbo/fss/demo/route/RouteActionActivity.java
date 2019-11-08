package com.gongbo.fss.demo.route;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.router.annotation.Route;
//使用Action方式路由
@Route(action = "com.gongbo.fss.route.action")
@BindActivity(value = R.layout.activity_route_action, finishViewId = R.id.img_back)
public class RouteActionActivity extends BaseFssActivity {

}
