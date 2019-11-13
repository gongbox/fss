package com.gongbo.fss.demo.route;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.router.annotation.Route;

@Route(requestCode = 1234,withResultCallBack = true)
@BindActivity(R.layout.activity_route_callback)
public class RouteCallbackActivity extends BaseFssActivity {

    @BindOnClick(R.id.btn_back)
    private void back() {
        setResult(RESULT_OK);
        finish();
    }
}
