package com.gongbo.fss.demo.adapter.recyclerview;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gongbo.fss.adapter.recyclerview.BaseBindingSingleAdapter;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.adapter.ListDataModel;
import com.gongbo.fss.demo.databinding.LayoutBindingListItemBinding;
import com.gongbo.fss.router.annotation.Route;

@Route(group = "recyclerView")
@BindActivity(layout = R.layout.activity_recycler_view, finishView = R.id.img_back)
public class BaseBindingRecyclerViewSingleAdapterTestActivity extends BaseFssActivity {

    @BindView(id = R.id.recycler_view)
    private RecyclerView recyclerView;

    @Override
    protected void initView() {
        super.initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new BaseBindingSingleAdapter<String, LayoutBindingListItemBinding>(
                this,
                ListDataModel.getDatas(),
                com.gongbo.fss.demo.BR.value,
                R.layout.layout_binding_list_item));
    }

}
