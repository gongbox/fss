package com.fss.base;


import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.annotation.Keep;

/**
 * Created by Administrator on 2017/10/21.
 */

public abstract class BaseBindingFssActivity<VB extends ViewDataBinding> extends BaseFssActivity {

    @Keep
    protected VB binding;

    @Override
    protected void setContentLayout(int layoutResID) {
        binding = DataBindingUtil.setContentView(this, layoutResID);
    }

}
