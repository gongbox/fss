package com.gongbo.fss.demo.runpriority;

import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.router.annotation.Route;
import com.gongbo.fss.runpriority.annotation.RunPriority;
import com.gongbo.fss.runpriority.constant.Priority;

import static com.gongbo.fss.demo.util.ToastUtils.showToast;

@Route
@BindActivity(value = R.layout.activity_run_priority_test, finishViewId = R.id.img_back)
public class RunPriorityTestActivity extends BaseActivity {

    private int time = 0;

    //声明为高优先级，会优先调用
    @RunPriority(Priority.HIGH)
    protected void initView() {
        showToast("initView " + time++);
    }

    //声明为低优先级，会最后调用
    @RunPriority(Priority.LOW)
    protected void initData() {
        showToast("initData " + time++);
    }

    //声明为普通优先级，这也是默认的优先级，因此也可以不写
    //@RunPriority(Priority.NORMAL)
    protected void initListener() {
        showToast("initListener " + time++);
    }
}
