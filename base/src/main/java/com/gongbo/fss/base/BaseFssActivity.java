package com.gongbo.fss.base;


import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.gongbo.fss.bind.FssBind;
import com.gongbo.fss.runpriority.RunPriorityUtils;
import com.gongbo.fss.runpriority.model.RunPriorityInfo;


/**
 * Created by Administrator on 2017/10/21.
 */

public abstract class BaseFssActivity extends AppCompatActivity {

    private static final String TAG = "BaseFssActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(FssBind.getLayoutId(this));
        FssBind.bind(this, BaseFssActivity.class);

        //构造运行优先级方法
        RunPriorityInfo runPriorityInfo = new RunPriorityInfo.Builder(this)
                .addMethod("initView")
                .addMethod("initData")
                .addMethod("initListener")
                .build();
        //调用运行优先级方法，默认调用顺序为:initView() -> initData() -> initListener(),子类可使用@RunPriority注解自定义调用顺序
        RunPriorityUtils.callAll(runPriorityInfo);
    }

    protected void setContentLayout(@LayoutRes int layoutResID) {
        setContentView(layoutResID);
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }


}
