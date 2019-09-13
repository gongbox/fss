package com.gongbo.fss.demo.adapter.listview;

import android.content.Context;
import android.widget.ListView;

import androidx.databinding.ViewDataBinding;


import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.adapter.listview.BaseBindingSimpleAdapter;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.demo.BR;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.router.annotation.Route;

import java.util.Arrays;
import java.util.List;

@Route
@BindActivity(layout = R.layout.activity_list_view)
public class BaseBindingSimpleAdapterTestActivity extends BaseFssActivity {

    @BindView(id = R.id.list_view)
    private ListView listView;

    private BindingAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        listView.setAdapter(adapter = new BindingAdapter(this, Arrays.asList("1", "2", "3")));
    }
}

class BindingAdapter extends BaseBindingSimpleAdapter<String> {

    public BindingAdapter(Context context, List<String> datas) {
        super(context, datas, com.gongbo.fss.demo.BR.value, R.layout.layout_binding_list_item);
    }

    @Override
    protected void onBindView(ViewDataBinding viewDataBinding, String s, int position) {
        super.onBindView(viewDataBinding, s, position);
    }
}



