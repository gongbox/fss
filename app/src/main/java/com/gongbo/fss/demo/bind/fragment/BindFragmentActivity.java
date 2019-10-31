package com.gongbo.fss.demo.bind.fragment;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.router.annotation.Route;

@Route
@BindActivity(value = R.layout.activity_bind_fragment, finishViewId = R.id.img_back)
public class BindFragmentActivity extends BaseActivity {

}
