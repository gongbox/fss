package com.gongbo.fss.base;


import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.gongbo.fss.bind.FssBind;
import com.gongbo.fss.runpriority.RunPriorityUtils;


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
        RunPriorityUtils.callAll(this, "initView", "initListener", "initData");
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
