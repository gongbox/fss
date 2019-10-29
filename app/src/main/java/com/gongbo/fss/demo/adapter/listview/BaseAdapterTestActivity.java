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
@BindActivity(layout = R.layout.activity_list_view, finishView = R.id.img_back)
public class BaseAdapterTestActivity extends BaseFssActivity {

    @BindView(id = R.id.list_view)
    private ListView listView;

    @Override
    protected void initView() {
        super.initView();

        listView.setAdapter(new Adapter(this, ListDataModel.getDatas()));
    }

    static class Adapter extends BaseAdapter<String, Adapter.ViewHolder> {

        public Adapter(Context context, List<String> datas) {
            super(context, datas, R.layout.layout_list_item);
        }

        @Override
        protected void onBindView(ViewHolder holder, String s, int position) {
            holder.tvText.setText(s);
        }

        static class ViewHolder extends BaseViewHolder {
            TextView tvText;

            public ViewHolder(View view) {
                super(view);
                this.tvText = findViewById(R.id.tv_text);
            }
        }
    }
}



