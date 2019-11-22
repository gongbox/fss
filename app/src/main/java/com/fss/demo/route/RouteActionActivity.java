package com.fss.demo.route;

import com.fss.base.BaseFssActivity;
import com.fss.bind.annotation.BindActivity;
import com.fss.demo.R;
import com.fss.router.annotation.Route;
//使用Action方式路由
@Route(action = "com.fss.route.action")
@BindActivity(value = R.layout.activity_route_action, finishViewId = R.id.img_back)
public class RouteActionActivity extends BaseFssActivity {

}
