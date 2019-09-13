package com.gongbo.fss.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gongbo.fss.bind.FssBind;
import com.gongbo.fss.runpriority.RunPriorityUtils;


public abstract class BaseFssFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(FssBind.getLayoutId(this), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FssBind.bind(this, BaseFssFragment.class);
        RunPriorityUtils.callAll(this, "initView", "initListener", "initData");
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }

}
