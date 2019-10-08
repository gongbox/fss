package com.gongbo.fss.demo.adapter.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gongbo.fss.adapter.recyclerview.BaseRecyclerViewAdapter;
import com.gongbo.fss.adapter.recyclerview.viewholder.BaseRecyclerViewHolder;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.router.annotation.Route;

import java.util.Arrays;
import java.util.List;

@Route
@BindActivity(layout = R.layout.activity_recycler_view)
public class BaseRecyclerViewAdapterTestActivity extends BaseFssActivity {

    @BindView(id = R.id.recycler_view)
    private RecyclerView recyclerView;

    private Adapter adapter;

    @Override
    protected void initView() {
        super.initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter = new Adapter(this, Arrays.asList("1", "2", "3")));
    }
}

class Adapter extends BaseRecyclerViewAdapter<String, Adapter.ViewHolder> {

    public Adapter(Context context, List<String> datas) {
        super(context, datas, R.layout.layout_list_item);
    }

    @Override
    public void onBindView(@NonNull ViewHolder holder, String s, int position) {
        super.onBindView(holder, s, position);
        holder.tvText.setText(s);
    }

    class ViewHolder extends BaseRecyclerViewHolder {
        TextView tvText;

        public ViewHolder(View view) {
            super(view);
            this.tvText = findViewById(R.id.tv_text);
        }
    }
}

