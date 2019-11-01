package com.gongbo.fss.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.gongbo.fss.bind.FssBind;


public abstract class BaseBindingFssFragment<VB extends ViewDataBinding> extends BaseFssFragment {

    protected VB binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取布局
        int layoutId = FssBind.getLayoutId(this);
        //绑定布局
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        return binding.getRoot();
    }
}
