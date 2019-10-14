package com.gongbo.fss.demo.bind;

import android.view.View;

import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.bind.annotation.BindRoute;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.router.annotation.Route;

@Route
@BindActivity(layout = R.layout.activity_bind_test)
@BindRoute(id = R.id.btn_route, toActivity = BindDetailActivity.class,
        extras = {":@value", "value2:789", "value3:(int)[234]"})
@BindOnClick(id = R.id.btn_test, method = "test")
public class BindTestActivity extends BaseActivity {

    private String value = "123456";

    @BindOnClick(id = R.id.btn_click)
    private void click(View view) {
        showToast("click");
    }

    private void test() {
        showToast("test");
    }
}
