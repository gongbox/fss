package com.fss.demo.bind.activity;

import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindExtra;
import com.fss.bind.annotation.BindOnClick;
import com.fss.demo.BR;
import com.fss.demo.R;
import com.fss.demo.base.BaseBindingActivity;
import com.fss.demo.databinding.ActivityBindDetailBinding;
import com.fss.router.annotation.Route;

import static com.fss.demo.util.ToastUtils.showToast;


@Route
@BindActivity(value = R.layout.activity_bind_detail, finishViewId = R.id.img_back)
@BindExtra(value = "value", bindingId = BR.value1)
public class BindDetailActivity extends BaseBindingActivity<ActivityBindDetailBinding> {

    @BindExtra(bindingId = BR.value2)
    private String value2;

    @BindExtra
    private Integer value3;

    @BindOnClick(R.id.btn_show_value)
    private void onClick() {
        showToast(value3 + "");
    }

    protected void initData() {
    }
}
