package com.fss.demo.bind.fragment;

import com.fss.bind.annotation.BindActivity;
import com.fss.demo.R;
import com.fss.demo.base.BaseActivity;
import com.fss.router.annotation.Route;

@Route
@BindActivity(value = R.layout.activity_bind_fragment, finishViewId = R.id.img_back)
public class BindFragmentActivity extends BaseActivity {

}
