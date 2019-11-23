package com.fss.demo.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fss.router.FssRouteApi;
import com.fss.adapter.listview.CommonAdapter;
import com.fss.base.BaseFssActivity;
import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindView;
import com.fss.demo.R;
import com.fss.demo.base.BaseActivity;
import com.fss.router.annotation.Route;

import java.util.Arrays;
import java.util.List;



@Route
@BindActivity(value = R.layout.activity_adapter, finishViewId = R.id.img_back)
public class AdapterActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.list_view)
    private ListView listView;

    private List<String> datas = Arrays.asList(
            "ListView : BaseAdapter",
            "ListView : BaseAdapter(绑定)",
            "ListView : BaseBindingAdapter",
            "ListView : BaseBindingSingleAdapter",
            "ListView : CommonAdapter",
            "RecyclerView : BaseAdapter",
            "RecyclerView : BaseAdapter(绑定)",
            "RecyclerView : BaseBindingAdapter",
            "RecyclerView : BaseBindingSingleAdapter",
            "RecyclerView : CommonAdapter"
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
                FssRouteApi.RECYCLER_VIEW.navigateToBaseAdapterTestActivity(this);
                break;
            case 6:
                FssRouteApi.RECYCLER_VIEW.navigateToBaseBindAdapterTestActivity(this);
                break;
            case 7:
                FssRouteApi.RECYCLER_VIEW.navigateToBaseBindingAdapterTestActivity(this);
                break;
            case 8:
                FssRouteApi.RECYCLER_VIEW.navigateToBaseBindingSingleAdapterTestActivity(this);
                break;
            case 9:
                FssRouteApi.RECYCLER_VIEW.navigateToCommonAdapterTestActivity(this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }

}

