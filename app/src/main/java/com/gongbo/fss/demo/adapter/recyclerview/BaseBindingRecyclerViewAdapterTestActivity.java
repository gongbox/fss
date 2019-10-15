package com.gongbo.fss.demo.adapter.recyclerview;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gongbo.fss.adapter.recyclerview.BaseBindingAdapter;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.router.annotation.Route;

import java.util.ArrayList;
import java.util.List;

@Route
@BindActivity(layout = R.layout.activity_recycler_view, finish = R.id.img_back)
public class BaseBindingRecyclerViewAdapterTestActivity extends BaseFssActivity {

    @BindView(id = R.id.recycler_view)
    private RecyclerView recyclerView;

    private BaseBindingAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(i + "");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter = new BaseBindingAdapter<>(this, datas, com.gongbo.fss.demo.BR.value, R.layout.layout_binding_list_item));
    }

}