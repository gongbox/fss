package com.gongbo.fss.demo.bind.fragment;


import com.gongbo.fss.base.BaseFssFragment;
import com.gongbo.fss.bind.annotation.BindFragment;
import com.gongbo.fss.demo.R;

import static com.gongbo.fss.demo.util.ToastUtils.showToast;

@BindFragment(R.layout.fragment_bind_test)
public class BindTestFragment extends BaseFssFragment {

    @Override
    protected void initView() {
        showToast("hello");
    }
}
