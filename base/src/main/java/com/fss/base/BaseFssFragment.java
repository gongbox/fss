package com.fss.base;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fss.bind.FssBind;
import com.fss.runpriority.RunPriorityUtils;
import com.fss.runpriority.model.RunPriorityInfo;


public abstract class BaseFssFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取布局
        int layoutId = FssBind.getLayoutId(this);
        //根据布局填充
        return inflater.inflate(layoutId, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //绑定参数（只绑定BaseFssFragment及其子类的参数）
        FssBind.bind(this, BaseFssFragment.class);

        //构造运行优先级方法
        RunPriorityInfo runPriorityInfo = new RunPriorityInfo.Builder(this)
                .addMethod("initView")
                .addMethod("initData")
                .addMethod("initListener")
                .build();
        //调用运行优先级方法，默认调用顺序为:initView() -> initData() -> initListener(),子类可使用@RunPriority注解自定义调用顺序
        RunPriorityUtils.call(runPriorityInfo);
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }

}
