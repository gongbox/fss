package com.fss.demo.route;

import com.fss.base.BaseFssActivity;
import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindOnClick;
import com.fss.demo.R;
import com.fss.router.annotation.Route;

@Route(requestCode = 1234,withResultCallBack = true)
@BindActivity(R.layout.activity_route_callback)
public class RouteCallbackActivity extends BaseFssActivity {

    @BindOnClick(R.id.btn_back)
    private void back() {
        setResult(RESULT_OK);
        finish();
    }
}
