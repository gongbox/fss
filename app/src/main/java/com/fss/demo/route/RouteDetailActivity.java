package com.fss.demo.route;

import android.content.Intent;

import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindExtra;
import com.fss.demo.R;
import com.fss.demo.base.BaseActivity;
import com.fss.router.annotation.DefaultExtra;
import com.fss.router.annotation.Route;
import com.fss.router.annotation.RouteExtra;

import static com.fss.demo.util.ToastUtils.showToast;

@Route(
        //设置路由名称
        name = "navigateToRouteDetailActivity",
        //设置分组
        group = "detail",
        //添加requestCode
        requestCode = 1234,
        //设置category
        category = Intent.CATEGORY_DEFAULT,
        //设置falgs
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK,
        //添加参数
        routeExtras = {
                @RouteExtra(name = "value", type = String.class, desc = "value")
        },
        //添加默认参数
        defaultExtras = {
                @DefaultExtra(name = "defaultValue", defaultValue = "hello")
        },
        //activity进入动画
        enterAnim = android.R.anim.fade_in,
        exitAnim = android.R.anim.fade_out,
        //添加注释
        desc = "示例"
)
@BindActivity(value = R.layout.activity_route_detail, finishViewId = R.id.img_back)
public class RouteDetailActivity extends BaseActivity {

    @BindExtra("value")
    private String value;

    @BindExtra
    private String defaultValue;

    protected void initData() {
        showToast(value);
        showToast(defaultValue);
    }
}
