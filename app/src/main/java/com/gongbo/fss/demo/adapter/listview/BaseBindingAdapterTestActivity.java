package com.gongbo.fss.demo.adapter.listview;

import android.widget.ListView;

import com.gongbo.fss.adapter.listview.BaseBindingAdapter;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.adapter.ListDataModel;
import com.gongbo.fss.router.annotation.Route;

@Route(group = "listView")
@BindActivity(value = R.layout.activity_list_view, finishViewId = R.id.img_back)
public class BaseBindingAdapterTestActivity extends BaseFssActivity {

    @BindView(R.id.list_view)
    private ListView listView;

    @Override
    protected void initView() {
        super.initView();
        listView.setAdapter(new BaseBindingAdapter<>(
                this,
                ListDataModel.getDatas(),
                com.gongbo.fss.demo.BR.value,
                R.layout.layout_binding_list_item));
    }
}



