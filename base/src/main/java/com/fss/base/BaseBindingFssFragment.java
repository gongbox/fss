package com.fss.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fss.bind.FssBind;


public abstract class BaseBindingFssFragment<VB extends ViewDataBinding> extends BaseFssFragment {

    @Keep
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
