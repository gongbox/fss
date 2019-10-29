package com.gongbo.fss.demo.bind;

import android.view.View;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.bind.annotation.BindRoute;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.bind.fragment.BindFragmentActivity;
import com.gongbo.fss.router.annotation.Route;

import static com.gongbo.fss.demo.util.ToastUtils.showToast;

@Route
@BindActivity(layout = R.layout.activity_bind_test, finishView = R.id.img_back)
@BindRoute(id = R.id.btn_route,
        action = "com.gongbo.fss.bind.detail",
        extras = {":@value", "value2:789", "value3:(int)[234]"},
        enterAnim = android.R.anim.slide_in_left
)
//@BindRoute(id = R.id.btn_route,
//        toActivity = BindDetailActivity.class,
//        extras = {":@value", "value2:789", "value3:(int)[234]"},
//        enterAnim = android.R.anim.slide_in_left
//)
@BindRoute(id = R.id.btn_route_fragment, toActivity = BindFragmentActivity.class)
@BindOnClick(id = R.id.btn_test, method = "test")
public class BindTestActivity extends BaseFssActivity {

    private String value = "123456";

    @BindOnClick(id = R.id.btn_click)
    private void click(View view) {
        showToast("click");
    }

    private void test() {
        showToast("test");
    }
}
