package com.gongbo.fss.demo.route;

import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindExtra;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.router.annotation.DefaultExtra;
import com.gongbo.fss.router.annotation.Route;
import com.gongbo.fss.router.annotation.RouteExtra;

//普通路由
//@Route
//使用Action方式路由
@Route(action = "com.gongbo.fss.route.detail")
//使用分组
@Route(
        //设置路由名称
        name = "navigateToRouteDetailActivity",
        //设置分组
        group = "detail",
        //添加requestCode
        requestCode = 1234,
        //设置category
//        category = "",
        //设置falgs
//        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK,
        //添加参数
        routeExtras = {
                @RouteExtra(name = "value", type = String.class)
        },
        //添加默认参数
        defaultExtras = {
                @DefaultExtra(name = "defaultValue", defaultValue = "hello")
        },
        //activity进入动画
        enterAnim = android.R.anim.slide_in_left,
        //添加注释
        desc = "示例"
)
@BindActivity(layout = R.layout.activity_route_detail, finish = R.id.img_back)
public class RouteDetailActivity extends BaseActivity {

    @BindExtra(name = "value")
    private String value;

    @BindExtra
    private String defaultValue;

    @Override
    protected void initData() {
        showToast(value);
        showToast(defaultValue);
    }
}
