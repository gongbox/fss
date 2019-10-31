package com.gongbo.fss.demo.adapter.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gongbo.fss.adapter.recyclerview.BaseAdapter;
import com.gongbo.fss.adapter.recyclerview.viewholder.BaseViewHolder;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.adapter.ListDataModel;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.router.annotation.Route;

import java.util.List;

@Route(group = "recyclerView")
@BindActivity(value = R.layout.activity_recycler_view, finishViewId = R.id.img_back)
public class BaseBindRecyclerViewAdapterTestActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    private RecyclerView recyclerView;

    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new BindAdapter(this, ListDataModel.getDatas()));
    }

    static class BindAdapter extends BaseAdapter<String, BindAdapter.BindViewHolder> {

        public BindAdapter(Context context, List<String> datas) {
            super(context, datas, R.layout.layout_list_item);
        }

        @Override
        public void onBindView(@NonNull BindViewHolder holder, String s, int position) {
            super.onBindView(holder, s, position);
            holder.tvText.setText(s);
        }

        static class BindViewHolder extends BaseViewHolder {
            @BindView(R.id.tv_text)
            TextView tvText;

            public BindViewHolder(View view) {
                super(view);
            }
        }
    }
}

