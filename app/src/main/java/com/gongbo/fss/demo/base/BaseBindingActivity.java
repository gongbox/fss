package com.gongbo.fss.demo.base;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

/**
 * Created by Administrator on 2017/10/21.
 */

public class BaseBindingActivity<VB extends ViewDataBinding> extends BaseActivity {

    protected VB binding;

    @Override
    protected void setContentLayout(int layoutResID) {
        binding = DataBindingUtil.setContentView(this, layoutResID);
    }
}
