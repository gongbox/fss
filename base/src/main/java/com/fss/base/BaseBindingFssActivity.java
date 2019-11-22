package com.fss.base;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

/**
 * Created by Administrator on 2017/10/21.
 */

public abstract class BaseBindingFssActivity<VB extends ViewDataBinding> extends BaseFssActivity {

    protected VB binding;

    @Override
    protected void setContentLayout(int layoutResID) {
        binding = DataBindingUtil.setContentView(this, layoutResID);
    }

}
