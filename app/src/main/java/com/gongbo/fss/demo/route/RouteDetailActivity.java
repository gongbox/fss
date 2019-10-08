package com.gongbo.fss.demo.route;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.bind.annotation.BindParam;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.router.annotation.Autowired;
import com.gongbo.fss.router.annotation.DefaultExtra;
import com.gongbo.fss.router.annotation.Route;
import com.gongbo.fss.router.annotation.RouteExtra;
import com.gongbo.fss.router.api.FssRouter;

@Route(desc = "跳转到详情页")
@Route(routeExtras = {
        @RouteExtra(name = "value", type = String.class)
})
@Route(name = "routeToDetailWithDefaultValue", defaultExtras = {
        @DefaultExtra(name = "value", defaultValue = "1")
})
@Route(requestCode = 123, withResultCallBack = true)
@BindActivity(layout = R.layout.activity_route_detail)
public class RouteDetailActivity extends BaseFssActivity {
    private static final String TAG = "RouteHandler";

    @Autowired
    private String value = "";

    @Override
    protected void initData() {
        super.initData();
        FssRouter.getInstance().reject(this, getIntent());
        Toast.makeText(this, "value:" + value, Toast.LENGTH_SHORT).show();
    }

    @BindOnClick(id = {R.id.btn_finish, R.id.btn_finish_with_result})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_finish:
                finish();
                break;
            case R.id.btn_finish_with_result:
                Intent intent = new Intent();
                intent.putExtra("value", "4321");
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
