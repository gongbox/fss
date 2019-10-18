package com.gongbo.fss.demo.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gongbo.fss.adapter.listview.CommonAdapter;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.router.annotation.Route;

import java.util.Arrays;
import java.util.List;

import static com.gongbo.fss.router.FssRouteApi.ListViewRouteApi;
import static com.gongbo.fss.router.FssRouteApi.RecyclerViewRouteApi;


@Route
@BindActivity(layout = R.layout.activity_adapter, finish = R.id.img_back)
public class AdapterActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(id = R.id.list_view)
    private ListView listView;

    private List<String> datas = Arrays.asList(
            "BaseAdapter",
            "BaseBindAdapter",
            "BaseBindingAdapter",
            "BaseBindingSingleAdapter",
            "CommonAdapter",
            "BaseAdapter",
            "BaseBindRecyclerViewAdapter",
            "BaseBindingAdapter",
            "CommonRecyclerAdapter"
    );

    @Override
    protected void initView() {
        super.initView();
        listView.setAdapter(new CommonAdapter<>(this, datas, R.layout.layout_list_item,
                (holder, s, position) -> holder.setText(R.id.tv_text, s)));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                ListViewRouteApi.navigateToBaseAdapterTestActivity(this);
                break;
            case 1:
                ListViewRouteApi.navigateToBaseBindAdapterTestActivity(this);
                break;
            case 2:
                ListViewRouteApi.navigateToBaseBindingAdapterTestActivity(this);
                break;
            case 3:
                ListViewRouteApi.navigateToBaseBindingSingleAdapterTestActivity(this);
                break;
            case 4:
                ListViewRouteApi.navigateToCommonAdapterTestActivity(this);
                break;
            case 5:
                RecyclerViewRouteApi.navigateToBaseRecyclerViewAdapterTestActivity(this);
                break;
            case 6:
                RecyclerViewRouteApi.navigateToBaseBindRecyclerViewAdapterTestActivity(this);
                break;
            case 7:
                RecyclerViewRouteApi.navigateToBaseBindingRecyclerViewAdapterTestActivity(this);
                break;
            case 8:
                RecyclerViewRouteApi.navigateToCommonRecyclerAdapterViewTestActivity(this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }

}

