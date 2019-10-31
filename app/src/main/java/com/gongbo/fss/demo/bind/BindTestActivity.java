package com.gongbo.fss.demo.bind;

import android.view.View;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.bind.annotation.BindRoute;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.demo.bind.fragment.BindFragmentActivity;
import com.gongbo.fss.router.annotation.Route;

import static com.gongbo.fss.demo.util.ToastUtils.showToast;

@Route
@BindActivity(value = R.layout.activity_bind_test, finishViewId = R.id.img_back)
@BindRoute(viewId = R.id.btn_route,
        action = "com.gongbo.fss.bind.detail",
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

    private String value = "123456";

    @BindOnClick(R.id.btn_click)
    private void click(View view) {
        showToast("click");
    }

    private void test() {
        showToast("test");
    }
}
