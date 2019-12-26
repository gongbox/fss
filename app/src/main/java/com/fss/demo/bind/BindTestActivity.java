package com.fss.demo.bind;

import android.support.annotation.Keep;
import android.view.View;

import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindOnClick;
import com.fss.bind.annotation.BindRoute;
import com.fss.demo.R;
import com.fss.demo.base.BaseActivity;
import com.fss.demo.bind.fragment.BindFragmentActivity;
import com.fss.router.annotation.Route;

import static com.fss.demo.util.ToastUtils.showToast;

@Route
@BindActivity(value = R.layout.activity_bind_test, finishViewId = R.id.img_back)
//@BindRoute(viewId = R.id.btn_route,
//        action = "com.fss.bind.detail",
//        extras = {":@value", "value2:789", "value3:(int)[234]"},
//        enterAnim = android.R.anim.slide_in_left
//)
@BindRoute(viewId = R.id.btn_route,
        destination = "com.fss.demo.bind.activity.BindDetailActivity",
        extras = {":@value", "value2:789", "value3:(int)[234]"},
        enterAnim = android.R.anim.slide_in_left
)
//@BindRoute(R.value.btn_route,
//        toActivity = BindDetailActivity.class,
//        extras = {":@value", "value2:789", "value3:(int)[234]"},
//        enterAnim = android.R.anim.slide_in_left
//)
@BindRoute(viewId = R.id.btn_route_fragment, toActivity = BindFragmentActivity.class)
@BindOnClick(value = R.id.btn_test, onClickMethod = "test")
public class BindTestActivity extends BaseActivity {

    //@BindRoute中使用到，反射调用，所以要防止混淆
    @Keep
    private String value = "123456";

    @BindOnClick(R.id.btn_click)
    private void click(View view) {
        showToast("click");
    }

    //@BindOnClick中使用到，反射调用，所以要防止混淆
    @Keep
    private void test() {
        showToast("test");
    }
}
