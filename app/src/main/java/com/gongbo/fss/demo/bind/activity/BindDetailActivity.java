package com.gongbo.fss.demo.bind.activity;

import com.gongbo.fss.base.BaseBindingFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindExtra;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.demo.BR;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.databinding.ActivityBindDetailBinding;
import com.gongbo.fss.router.annotation.Route;

import static com.gongbo.fss.demo.util.ToastUtils.showToast;


@Route
@BindActivity(layout = R.layout.activity_bind_detail, finish = R.id.img_back)
@BindExtra(name = "value", id = BR.value1)
public class BindDetailActivity extends BaseBindingFssActivity<ActivityBindDetailBinding> {

    @BindExtra(id = BR.value2)
    private String value2;

    @BindExtra
    private Integer value3;

    @BindOnClick(id = R.id.btn_show_value)
    private void onClick() {
        showToast(value3 + "");
    }
}
