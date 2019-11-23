package com.fss.demo.adapter.listview;

import android.widget.ListView;

import com.fss.adapter.listview.BaseBindingAdapter;
import com.fss.base.BaseFssActivity;
import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindView;
import com.fss.demo.R;
import com.fss.demo.adapter.ListDataModel;
import com.fss.demo.base.BaseActivity;
import com.fss.router.annotation.Route;

@Route(group = "listView")
@BindActivity(value = R.layout.activity_list_view, finishViewId = R.id.img_back)
public class BaseBindingAdapterTestActivity extends BaseActivity {

    @BindView(R.id.list_view)
    private ListView listView;

    protected void initView() {
        listView.setAdapter(new BaseBindingAdapter<>(
                this,
                ListDataModel.getDatas(),
                com.fss.demo.BR.value,
                R.layout.layout_binding_list_item));
    }
}



