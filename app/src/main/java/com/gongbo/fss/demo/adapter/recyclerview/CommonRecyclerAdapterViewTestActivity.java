package com.gongbo.fss.demo.adapter.recyclerview;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.router.annotation.Route;
import com.gongbo.fss.adapter.recyclerview.CommonRecyclerViewAdapter;
import com.gongbo.fss.adapter.recyclerview.viewholder.RecyclerViewCommonViewHolder;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.demo.R;

import java.util.Arrays;

@Route
@BindActivity(layout = R.layout.activity_recycler_view)
public class CommonRecyclerAdapterViewTestActivity extends BaseFssActivity {

    @BindView(id = R.id.recycler_view)
    private RecyclerView recyclerView;

    private CommonRecyclerViewAdapter<String> adapter;

    @Override
    protected void initView() {
        super.initView();
        adapter = new CommonRecyclerViewAdapter<String>(this, Arrays.asList("1", "2", "3"), R.layout.layout_list_item) {
            @Override
            public void onBindView(@NonNull RecyclerViewCommonViewHolder holder, String s, int position) {
                super.onBindView(holder, s, position);
                holder.setText(R.id.tv_text, s);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
