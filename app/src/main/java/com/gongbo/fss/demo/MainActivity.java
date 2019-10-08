package com.gongbo.fss.demo;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gongbo.fss.adapter.listview.CommonAdapter;
import com.gongbo.fss.adapter.listview.viewholder.CommonViewHolder;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.router.FssRouteApi;
import com.gongbo.fss.router.annotation.Route;

import java.util.Arrays;
import java.util.List;

@Route
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
            "BindTestActivity",
            "RouteTestActivity"
    );

    @Override
    protected void initView() {
        super.initView();
        listView.setAdapter(adapter = new CommonAdapter<String>(this, datas, R.layout.layout_list_item) {
            @Override
            protected void setView(CommonViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_text, s);
            }
        });
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                FssRouteApi.DefaultRouteApi.routeToBaseSimpleAdapterTestActivity(this);
                break;
            case 1:
                 FssRouteApi.DefaultRouteApi.routeToBaseBindSimpleAdapterTestActivity(this);
                break;
            case 2:
                 FssRouteApi.DefaultRouteApi.routeToBaseBindingSimpleAdapterTestActivity(this);
                break;
            case 3:
                 FssRouteApi.DefaultRouteApi.routeToBaseBindingSimpleSingleAdapterTestActivity(this);
                break;
            case 4:
                 FssRouteApi.DefaultRouteApi.routeToCommonAdapterTestActivity(this);
                break;
            case 5:
                 FssRouteApi.DefaultRouteApi.routeToBaseRecyclerViewAdapterTestActivity(this);
                break;
            case 6:
                 FssRouteApi.DefaultRouteApi.routeToBaseBindRecyclerViewAdapterTestActivity(this);
                break;
            case 7:
                 FssRouteApi.DefaultRouteApi.routeToBaseBindingRecyclerViewAdapterTestActivity(this);
                break;
            case 8:
                 FssRouteApi.DefaultRouteApi.routeToCommonRecyclerAdapterViewTestActivity(this);
                break;
            case 9:
                 FssRouteApi.DefaultRouteApi.routeToRunPriorityTestActivity(this);
                break;
            case 10:
                 FssRouteApi.DefaultRouteApi.routeToBindTestActivity(this);
                break;
            case 11:
                FssRouteApi.DefaultRouteApi.routeToRouteMainActivity(this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }
}
