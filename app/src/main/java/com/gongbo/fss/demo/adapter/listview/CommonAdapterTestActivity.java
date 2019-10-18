package com.gongbo.fss.demo.adapter.listview;

import android.widget.ListView;
import android.widget.TextView;

import com.gongbo.fss.adapter.listview.CommonAdapter;
import com.gongbo.fss.adapter.listview.viewholder.CommonViewHolder;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.adapter.ListDataModel;
import com.gongbo.fss.router.annotation.Route;

@Route(group = "listView")
@BindActivity(layout = R.layout.activity_list_view, finish = R.id.img_back)
public class CommonAdapterTestActivity extends BaseFssActivity {

    @BindView(id = R.id.list_view)
    private ListView listView;

    @Override
    protected void initView() {
        super.initView();
        listView.setAdapter(new CommonAdapter<String>(this, ListDataModel.getDatas(), R.layout.layout_list_item) {
            @Override
            protected void onBindView(CommonViewHolder holder, String str, int position) {
                TextView textView = holder.getView(R.id.tv_text);
                textView.setText(str);
                //可以用holder.setText(R.id.tv_text, str);代替
            }
        });
    }
}
