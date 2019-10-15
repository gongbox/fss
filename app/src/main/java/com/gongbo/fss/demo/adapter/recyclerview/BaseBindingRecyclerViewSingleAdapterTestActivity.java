package com.gongbo.fss.demo.adapter.recyclerview;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gongbo.fss.adapter.recyclerview.BaseBindingRecyclerViewSingleAdapter;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.databinding.LayoutBindingListItemBinding;
import com.gongbo.fss.router.annotation.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route
@BindActivity(layout = R.layout.activity_recycler_view, finish = R.id.img_back)
public class BaseBindingRecyclerViewSingleAdapterTestActivity extends BaseFssActivity {

    @BindView(id = R.id.recycler_view)
    private RecyclerView recyclerView;

    private BindingSingleAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(i + "");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter = new BindingSingleAdapter(this, datas));
    }

    static class BindingSingleAdapter extends BaseBindingRecyclerViewSingleAdapter<String, LayoutBindingListItemBinding> {

        public BindingSingleAdapter(Context context, List<String> datas) {
            super(context, datas, com.gongbo.fss.demo.BR.value, R.layout.layout_binding_list_item);
        }

        @Override
        public void onBindView(@NonNull LayoutBindingListItemBinding viewDataBinding, String str, int position) {
            super.onBindView(viewDataBinding, str, position);
        }
    }
}
