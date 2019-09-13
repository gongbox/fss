package com.gongbo.fss.demo.adapter.listview;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.adapter.listview.BaseSimpleAdapter;
import com.gongbo.fss.adapter.listview.viewholder.BaseViewHolder;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.demo.R;

import java.util.Arrays;
import java.util.List;

@BindActivity(layout = R.layout.activity_list_view)
public class BaseBindSimpleAdapterTestActivity extends BaseFssActivity {

    @BindView(id = R.id.list_view)
    private ListView listView;

    private BindAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        listView.setAdapter(adapter = new BindAdapter(this, Arrays.asList("1", "2", "3")));
    }
}

class BindAdapter extends BaseSimpleAdapter<String, BindAdapter.BindViewHolder> {

    public BindAdapter(Context context, List<String> datas) {
        super(context, datas, R.layout.layout_list_item);
    }

    @Override
    protected void setView(BindViewHolder holder, String s, int position) {
        holder.tvText.setText(s);
    }

    class BindViewHolder extends BaseViewHolder {
        @BindView(id = R.id.tv_text)
        TextView tvText;

        public BindViewHolder(View itemView) {
            super(itemView);
        }
    }
}



