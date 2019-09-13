package com.gongbo.fss.demo.activity;

import android.widget.Toast;

import com.gongbo.demo.databinding.ActivityBindDetailBinding;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindBindingParam;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.bind.annotation.BindParam;
import com.gongbo.fss.base.BaseBindingFssActivity;
import com.gongbo.fss.demo.BR;
import com.gongbo.fss.demo.R;

@BindActivity(layout = R.layout.activity_bind_detail, finish = R.id.btn_finish)
@BindBindingParam(key = "EXTRA_VALUE1", id = BR.value1)
public class BindDetailActivity extends BaseBindingFssActivity<ActivityBindDetailBinding> {

    @BindBindingParam(key = "EXTRA_VALUE2", id = BR.value2)
    private String value2;

    @BindParam(key = "EXTRA_VALUE3")
    private Integer value3;

    @BindOnClick(id = R.id.btn_show_value)
    private void onClick() {
        Toast.makeText(this, value3 + "", Toast.LENGTH_SHORT).show();
    }
}
