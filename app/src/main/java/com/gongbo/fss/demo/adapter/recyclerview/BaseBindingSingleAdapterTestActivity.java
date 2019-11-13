package com.gongbo.fss.demo.adapter.recyclerview;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gongbo.fss.adapter.recyclerview.BaseBindingSingleAdapter;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.adapter.ListDataModel;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.demo.databinding.LayoutBindingListItemBinding;
import com.gongbo.fss.router.annotation.Route;

@Route(group = "recyclerView")
@BindActivity(value = R.layout.activity_recycler_view, finishViewId = R.id.img_back)
public class BaseBindingSingleAdapterTestActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    private RecyclerView recyclerView;

    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new BaseBindingSingleAdapter<String, LayoutBindingListItemBinding>(
                this,
                ListDataModel.getDatas(),
                com.gongbo.fss.demo.BR.value,
                R.layout.layout_binding_list_item));
    }

}
