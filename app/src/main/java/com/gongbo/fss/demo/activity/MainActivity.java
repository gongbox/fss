package com.gongbo.fss.demo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gongbo.fss.adapter.listview.CommonAdapter;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.adapter.listview.BaseBindSimpleAdapterTestActivity;
import com.gongbo.fss.demo.adapter.listview.BaseBindingSimpleAdapterTestActivity;
import com.gongbo.fss.demo.adapter.listview.BaseBindingSimpleSingleAdapterTestActivity;
import com.gongbo.fss.demo.adapter.listview.BaseSimpleAdapterTestActivity;
import com.gongbo.fss.demo.adapter.listview.CommonAdapterTestActivity;
import com.gongbo.fss.demo.adapter.recyclerview.BaseBindRecyclerViewAdapterTestActivity;
import com.gongbo.fss.demo.adapter.recyclerview.BaseBindingRecyclerViewAdapterTestActivity;
import com.gongbo.fss.demo.adapter.recyclerview.BaseRecyclerViewAdapterTestActivity;
import com.gongbo.fss.demo.adapter.recyclerview.CommonRecyclerAdapterViewTestActivity;

import java.util.Arrays;
import java.util.List;

@BindActivity(layout = R.layout.activity_list_view)
public class MainActivity extends BaseFssActivity implements AdapterView.OnItemClickListener {

    @BindView(id = R.id.list_view)
    private ListView listView;

    private CommonAdapter<String> adapter;

    private List<String> datas = Arrays.asList(
            "BaseSimpleAdapter",
            "BaseBindSimpleAdapter",
            "BaseBindingSimpleAdapter",
            "BaseBindingSimpleSingleAdapter",
            "CommonAdapter",
            "BaseRecyclerViewAdapter",
            "BaseBindRecyclerViewAdapter",
            "BaseBindingRecyclerViewAdapter",
            "CommonRecyclerViewAdapter",
            "RunPriorityTestActivity",
            "BindTestActivity"
    );

    @Override
    protected void initView() {
        super.initView();
//        listView.setAdapter(adapter = new CommonAdapter<String>(this, datas, R.layout.layout_list_item) {
//            @Override
//            protected void setView(CommonViewHolder holder, String s, int position) {
//                holder.setText(R.id.tv_text, s);
//            }
//        });
//        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, BaseSimpleAdapterTestActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, BaseBindSimpleAdapterTestActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, BaseBindingSimpleAdapterTestActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, BaseBindingSimpleSingleAdapterTestActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, CommonAdapterTestActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, BaseRecyclerViewAdapterTestActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, BaseBindRecyclerViewAdapterTestActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, BaseBindingRecyclerViewAdapterTestActivity.class));
                break;
            case 8:
                startActivity(new Intent(this, CommonRecyclerAdapterViewTestActivity.class));
                break;
            case 9:
                startActivity(new Intent(this, RunPriorityTestActivity.class));
                break;
            case 10:
                startActivity(new Intent(this, BindTestActivity.class));
                break;
        }
    }
}
