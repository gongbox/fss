package com.gongbo.fss.demo.bind;

import android.widget.Toast;

import com.gongbo.fss.base.BaseBindingFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindBindingExtra;
import com.gongbo.fss.bind.annotation.BindOnClick;
import com.gongbo.fss.bind.annotation.BindExtra;
import com.gongbo.fss.demo.BR;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.databinding.ActivityBindDetailBinding;
import com.gongbo.fss.router.annotation.Route;


@Route
@BindActivity(layout = R.layout.activity_bind_detail, finish = R.id.btn_finish)
@BindBindingExtra(name = "value", id = BR.value1)
public class BindDetailActivity extends BaseBindingFssActivity<ActivityBindDetailBinding> {

    @BindBindingExtra(name = "value2", id = BR.value2)
    private String value2;

    @BindExtra(name = "value3")
    private Integer value3;

    public BindDetailActivity() {
    }

    @BindOnClick(id = R.id.btn_show_value)
    private void onClick() {
        Toast.makeText(this, value3 + "", Toast.LENGTH_SHORT).show();
    }
}
