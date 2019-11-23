package com.fss.demo.runpriority;

import android.widget.ListView;

import com.fss.adapter.listview.CommonAdapter;
import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindView;
import com.fss.demo.R;
import com.fss.demo.base.BaseActivity;
import com.fss.router.annotation.Route;
import com.fss.runpriority.annotation.RunPriority;
import com.fss.runpriority.constant.Priority;

import java.util.ArrayList;
import java.util.List;

@Route
@BindActivity(value = R.layout.activity_list_view, finishViewId = R.id.img_back)
public class RunPriorityTestActivity extends BaseActivity {

    @BindView(R.id.list_view)
    private ListView listView;

    private List<String> datas = new ArrayList<>();

    private CommonAdapter<String> adapter = new CommonAdapter<>(this, datas, R.layout.layout_list_item,
            (holder, s, position) ->
                    holder.setText(R.id.tv_text, s));

    {
        datas.add("方法执行顺序为：");
    }

    //声明为高优先级，会优先调用
    @RunPriority(Priority.HIGH)
    protected void initView() {
        listView.setDivider(null);
        listView.setAdapter(adapter);
        datas.add("initView");
        adapter.notifyDataSetChanged();
    }

    //声明为低优先级，会最后调用
    @RunPriority(Priority.LOW)
    protected void initData() {
        datas.add("initData");
        adapter.notifyDataSetChanged();
    }

    //声明为普通优先级，这也是默认的优先级，因此也可以不写
    //@RunPriority(Priority.NORMAL)
    protected void initListener() {
        datas.add("initListener");
        adapter.notifyDataSetChanged();
    }
}
