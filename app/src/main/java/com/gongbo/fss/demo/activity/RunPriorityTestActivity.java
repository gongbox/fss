package com.gongbo.fss.demo.activity;

import android.renderscript.RenderScript;
import android.util.Log;

import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.router.annotation.Route;
import com.gongbo.fss.runpriority.Priority;
import com.gongbo.fss.runpriority.annotation.RunPriority;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.demo.R;

@Route
@BindActivity(layout = R.layout.activity_run_priority_test)
public class RunPriorityTestActivity extends BaseFssActivity {

    //声明为高优先级，会优先调用
    @RunPriority(priority = Priority.HIGH)
    @Override
    protected void initView() {
        super.initView();
        Log.i("RunPriority", "initView");
    }

    //声明为低优先级，会最后调用
    @RunPriority(priority = Priority.LOW)
    @Override
    protected void initData() {
        super.initData();
        Log.i("RunPriority", "initData");
    }

    //声明为普通优先级，这也是默认的优先级，因此也可以不写
    //@RunPriority(priority = Priority.NORMAL)
    @Override
    protected void initListener() {
        super.initListener();
        Log.i("RunPriority", "initListener");
    }
}
