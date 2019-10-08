package com.gongbo.fss.demo.route;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.router.FssRouteApi;
import com.gongbo.fss.router.annotation.Route;
import com.gongbo.fss.router.api.FssRouter;

@Route
@BindActivity(layout = R.layout.activity_route_test)
public class RouteTestActivity extends BaseFssActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FssRouter.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    @BindOnClick(id = {R.id.btn_detail, R.id.btn_detail_for_result, R.id.btn_detail_with_arg, R.id.btn_detail_with_default_arg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_detail:
                FssRouteApi.DefaultRouteApi.routeToRouteDetailActivity(this);
                break;
            case R.id.btn_detail_for_result:
                FssRouteApi.DefaultRouteApi.routeToRouteDetailActivity(this, (requestCode, resultCode, data) ->
                        Toast.makeText(RouteTestActivity.this, "value:" + data.getStringExtra("value"), Toast.LENGTH_SHORT).show());
                break;
            case R.id.btn_detail_with_arg:
                FssRouteApi.DefaultRouteApi.routeToRouteDetailActivity(this, "123");
                break;
            case R.id.btn_detail_with_default_arg:
                FssRouteApi.DefaultRouteApi.routeToDetailWithDefaultValue(this);
                break;
        }
    }

}
