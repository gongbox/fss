package com.gongbo.fss.demo.adapter.listview;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gongbo.fss.adapter.listview.BaseAdapter;
import com.gongbo.fss.adapter.listview.viewholder.BaseViewHolder;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.adapter.ListDataModel;
import com.gongbo.fss.router.annotation.Route;

import java.util.List;

@Route(group = "listView")
@BindActivity(layout = R.layout.activity_list_view, finish = R.id.img_back)
public class BaseBindAdapterTestActivity extends BaseFssActivity {

    @BindView(id = R.id.list_view)
    private ListView listView;

    @Override
    protected void initView() {
        super.initView();
        listView.setAdapter(new BindAdapter(this, ListDataModel.getDatas()));
    }

    static class BindAdapter extends BaseAdapter<String, BindAdapter.BindViewHolder> {

        public BindAdapter(Context context, List<String> datas) {
            super(context, datas, R.layout.layout_list_item);
        }

        @Override
        protected void onBindView(BindViewHolder holder, String s, int position) {
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
}


