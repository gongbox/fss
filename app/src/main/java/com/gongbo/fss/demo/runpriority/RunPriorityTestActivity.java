package com.gongbo.fss.demo.runpriority;

import android.widget.ListView;

import com.gongbo.fss.adapter.listview.CommonAdapter;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.router.annotation.Route;
import com.gongbo.fss.runpriority.annotation.RunPriority;
import com.gongbo.fss.runpriority.constant.Priority;

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

    //声明为高优先级，会优先调用
    @RunPriority(Priority.HIGH)
    protected void initView() {
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
