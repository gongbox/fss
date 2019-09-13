package com.fss.base;


import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;


import com.fss.bind.FssBind;
import com.fss.runpriority.RunPriorityUtils;
import com.fss.runpriority.model.RunPriorityInfo;


/**
 * Created by Administrator on 2017/10/21.
 */

public abstract class BaseFssActivity extends AppCompatActivity {

    private static final String TAG = "BaseFssActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取布局
        int layoutId = FssBind.getLayoutId(this);
        //设置布局
        setContentLayout(layoutId);
        //绑定参数（只绑定BaseFssActivity及其子类的参数）
        FssBind.bind(this, BaseFssActivity.class);

        //构造运行优先级方法
        RunPriorityInfo runPriorityInfo = new RunPriorityInfo.Builder(this)
                .addMethod("initView")
                .addMethod("initData")
                .addMethod("initListener")
                .build();
        //调用运行优先级方法，默认调用顺序为:initView() -> initData() -> initListener(),子类可使用@RunPriority注解自定义调用顺序
        RunPriorityUtils.call(runPriorityInfo);
    }

    protected void setContentLayout(@LayoutRes int layoutResID) {
        setContentView(layoutResID);
    }

    @Keep
    protected void initView() {
    }
    @Keep
    protected void initData() {
    }
    @Keep
    protected void initListener() {
    }


}
