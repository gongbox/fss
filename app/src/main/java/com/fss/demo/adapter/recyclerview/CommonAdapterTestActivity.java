package com.fss.demo.adapter.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fss.adapter.recyclerview.CommonAdapter;
import com.fss.adapter.recyclerview.viewholder.CommonViewHolder;
import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindView;
import com.fss.demo.R;
import com.fss.demo.adapter.ListDataModel;
import com.fss.demo.base.BaseActivity;
import com.fss.router.annotation.Route;

@Route(group = "recyclerView")
@BindActivity(value = R.layout.activity_recycler_view, finishViewId = R.id.img_back)
public class CommonAdapterTestActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    private RecyclerView recyclerView;

    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new CommonAdapter<String>(this, ListDataModel.getDatas(), R.layout.layout_list_item) {
            @Override
            public void onBindView(@NonNull CommonViewHolder holder, String s, int position) {
                super.onBindView(holder, s, position);
                holder.setText(R.id.tv_text, s);
            }
        });
    }
}
