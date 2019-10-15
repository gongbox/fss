package com.gongbo.fss.demo.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public class BaseBindingActivity<VB extends ViewDataBinding> extends BaseActivity {

    protected VB binding;

    @Override
    protected void setContentLayout(int layoutResID) {
        binding = DataBindingUtil.setContentView(this, layoutResID);
    }
}
