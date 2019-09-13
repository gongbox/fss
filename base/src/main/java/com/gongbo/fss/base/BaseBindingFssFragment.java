package com.gongbo.fss.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.gongbo.fss.bind.FssBind;


public abstract class BaseBindingFssFragment<VB extends ViewDataBinding> extends BaseFssFragment {
    protected VB binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, FssBind.getLayoutId(this), container, false);
        return binding.getRoot();
    }
}
