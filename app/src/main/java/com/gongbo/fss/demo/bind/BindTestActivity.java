package com.gongbo.fss.demo.bind;

import android.view.View;
import android.widget.Toast;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.bind.annotation.BindRoute;
import com.gongbo.fss.bind.annotation.RouteKey;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.router.annotation.Route;

@Route
@BindActivity(layout = R.layout.activity_bind_test)
@BindRoute(id = R.id.btn_route, toActivity = BindDetailActivity.class, extraFields = {"value1", "value2", "value3"})
@BindOnClick(id = R.id.btn_test, method = "test")
public class BindTestActivity extends BaseFssActivity {

    @RouteKey(key = "EXTRA_VALUE1")
    private String value1 = "123456";

    @RouteKey(key = "EXTRA_VALUE2")
    private String value2 = "789";

    @RouteKey(key = "EXTRA_VALUE3")
    private Integer value3 = 1234;

    @BindOnClick(id = R.id.btn_click)
    private void click(View view) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }

    private void test() {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }
}
