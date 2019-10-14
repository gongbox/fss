package com.gongbo.fss.demo.bind;

import android.widget.Toast;

import com.gongbo.fss.base.BaseBindingFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindBindingParam;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.bind.annotation.BindParam;
import com.gongbo.fss.demo.BR;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.databinding.ActivityBindDetailBinding;
import com.gongbo.fss.router.annotation.Route;


@Route
@BindActivity(layout = R.layout.activity_bind_detail, finish = R.id.btn_finish)
@BindBindingParam(key = "value", id = BR.value1)
public class BindDetailActivity extends BaseBindingFssActivity<ActivityBindDetailBinding> {

    @BindBindingParam(key = "value2", id = BR.value2)
    private String value2;

    @BindParam(key = "value3")
    private Integer value3;

    public BindDetailActivity() {
    }

    @BindOnClick(id = R.id.btn_show_value)
    private void onClick() {
        Toast.makeText(this, value3 + "", Toast.LENGTH_SHORT).show();
    }
}
