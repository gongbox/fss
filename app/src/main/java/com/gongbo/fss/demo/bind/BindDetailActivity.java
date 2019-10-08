package com.gongbo.fss.demo.bind;

import android.widget.Toast;

import com.gongbo.fss.base.BaseBindingFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindBindingParam;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.bind.annotation.BindParam;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.databinding.ActivityBindDetailBinding;
import com.gongbo.fss.router.annotation.Route;

@Route
@BindActivity(layout = R.layout.activity_bind_detail, finish = R.id.btn_finish)
@BindBindingParam(key = "EXTRA_VALUE1", id = com.gongbo.fss.demo.BR.value1)
public class BindDetailActivity extends BaseBindingFssActivity<ActivityBindDetailBinding> {

    @BindBindingParam(key = "EXTRA_VALUE2", id = com.gongbo.fss.demo.BR.value2)
    private String value2;

    @BindParam(key = "EXTRA_VALUE3")
    private Integer value3;

    public BindDetailActivity() {
    }

    @BindOnClick(id = R.id.btn_show_value)
    private void onClick() {
        Toast.makeText(this, value3 + "", Toast.LENGTH_SHORT).show();
    }
}
