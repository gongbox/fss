package com.gongbo.fss.demo.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gongbo.fss.adapter.listview.CommonAdapter;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseActivity;
import com.gongbo.fss.router.FssRouteApi;
import com.gongbo.fss.router.annotation.Route;

import java.util.Arrays;
import java.util.List;



@Route
@BindActivity(value = R.layout.activity_adapter, finishViewId = R.id.img_back)
public class AdapterActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.list_view)
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

    protected void initView() {
        listView.setAdapter(new CommonAdapter<>(this, datas, R.layout.layout_list_item,
                (holder, s, position) -> holder.setText(R.id.tv_text, s)));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                FssRouteApi.LIST_VIEW.navigateToBaseAdapterTestActivity(this);
                break;
            case 1:
                FssRouteApi.LIST_VIEW.navigateToBaseBindAdapterTestActivity(this);
                break;
            case 2:
                FssRouteApi.LIST_VIEW.navigateToBaseBindingAdapterTestActivity(this);
                break;
            case 3:
                FssRouteApi.LIST_VIEW.navigateToBaseBindingSingleAdapterTestActivity(this);
                break;
            case 4:
                FssRouteApi.LIST_VIEW.navigateToCommonAdapterTestActivity(this);
                break;
            case 5:
                FssRouteApi.RECYCLER_VIEW.navigateToBaseRecyclerViewAdapterTestActivity(this);
                break;
            case 6:
                FssRouteApi.RECYCLER_VIEW.navigateToBaseBindRecyclerViewAdapterTestActivity(this);
                break;
            case 7:
                FssRouteApi.RECYCLER_VIEW.navigateToBaseBindingRecyclerViewAdapterTestActivity(this);
                break;
            case 8:
                FssRouteApi.RECYCLER_VIEW.navigateToCommonRecyclerAdapterViewTestActivity(this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }

}

