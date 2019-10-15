package com.gongbo.fss.demo.adapter.listview;

import android.widget.ListView;

import com.gongbo.fss.adapter.listview.BaseBindingSingleAdapter;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.databinding.LayoutBindingListItemBinding;
import com.gongbo.fss.router.annotation.Route;

import java.util.ArrayList;
import java.util.List;

@Route
@BindActivity(layout = R.layout.activity_list_view, finish = R.id.img_back)
public class BaseBindingSimpleSingleAdapterTestActivity extends BaseFssActivity {

    @BindView(id = R.id.list_view)
    private ListView listView;

    private BaseBindingSingleAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(i + "");
        }
        listView.setAdapter(adapter = new BaseBindingSingleAdapter<String, LayoutBindingListItemBinding>(this,
                datas, com.gongbo.fss.demo.BR.value, R.layout.layout_binding_list_item));
    }

}



