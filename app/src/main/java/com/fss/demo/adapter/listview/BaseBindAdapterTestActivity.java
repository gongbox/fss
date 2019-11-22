package com.fss.demo.adapter.listview;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.fss.adapter.listview.BaseAdapter;
import com.fss.adapter.listview.viewholder.BaseViewHolder;
import com.fss.base.BaseFssActivity;
import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindView;
import com.fss.demo.R;
import com.fss.demo.adapter.ListDataModel;
import com.fss.demo.base.BaseActivity;
import com.fss.router.annotation.Route;

import java.util.List;

@Route(group = "listView")
@BindActivity(value = R.layout.activity_list_view, finishViewId = R.id.img_back)
public class BaseBindAdapterTestActivity extends BaseActivity {

    @BindView(R.id.list_view)
    private ListView listView;

    protected void initView() {
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
            @BindView(R.id.tv_text)
            TextView tvText;

            public BindViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}



