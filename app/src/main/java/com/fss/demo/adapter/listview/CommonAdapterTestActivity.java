package com.fss.demo.adapter.listview;

import android.widget.ListView;
import android.widget.TextView;

import com.fss.adapter.listview.CommonAdapter;
import com.fss.adapter.listview.viewholder.CommonViewHolder;
import com.fss.base.BaseFssActivity;
import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindView;
import com.fss.demo.R;
import com.fss.demo.adapter.ListDataModel;
import com.fss.demo.base.BaseActivity;
import com.fss.router.annotation.Route;

@Route(group = "listView")
@BindActivity(value = R.layout.activity_list_view, finishViewId = R.id.img_back)
public class CommonAdapterTestActivity extends BaseActivity {

    @BindView(R.id.list_view)
    private ListView listView;

    protected void initView() {
        listView.setAdapter(new CommonAdapter<String>(this, ListDataModel.getDatas(), R.layout.layout_list_item) {
            @Override
            protected void onBindView(CommonViewHolder holder, String str, int position) {
                TextView textView = holder.getView(R.id.tv_text);
                textView.setText(str);
                //可以用holder.setText(R.value.tv_text, str);代替
            }
        });
    }
}
