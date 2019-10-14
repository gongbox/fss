package com.gongbo.fss.demo;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gongbo.fss.adapter.listview.CommonAdapter;
import com.gongbo.fss.adapter.listview.viewholder.CommonViewHolder;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.router.annotation.Route;

import java.util.Arrays;
import java.util.List;

import static com.gongbo.fss.router.FssRouteApi.DefaultRouteApi;

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
                DefaultRouteApi.navigateToBaseSimpleAdapterTestActivity(this);
                break;
            case 1:
                DefaultRouteApi.navigateToBaseBindSimpleAdapterTestActivity(this);
                break;
            case 2:
                DefaultRouteApi.navigateToBaseBindingSimpleAdapterTestActivity(this);
                break;
            case 3:
                DefaultRouteApi.navigateToBaseBindingSimpleSingleAdapterTestActivity(this);
                break;
            case 4:
                DefaultRouteApi.navigateToCommonAdapterTestActivity(this);
                break;
            case 5:
                DefaultRouteApi.navigateToBaseRecyclerViewAdapterTestActivity(this);
                break;
            case 6:
                DefaultRouteApi.navigateToBaseBindRecyclerViewAdapterTestActivity(this);
                break;
            case 7:
                DefaultRouteApi.navigateToBaseBindingRecyclerViewAdapterTestActivity(this);
                break;
            case 8:
                DefaultRouteApi.navigateToCommonRecyclerAdapterViewTestActivity(this);
                break;
            case 9:
                DefaultRouteApi.navigateToRunPriorityTestActivity(this);
                break;
            case 10:
                DefaultRouteApi.navigateToBindTestActivity(this);
                break;
            case 11:
                DefaultRouteApi.navigateToRouteMainActivity(this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }
}
