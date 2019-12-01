package com.fss.demo;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.router.AppRouteApi;
import com.fss.adapter.listview.CommonAdapter;
import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindView;
import com.fss.demo.base.BaseActivity;
import com.fss.router.annotation.Route;

import java.util.Arrays;
import java.util.List;


@Route
@BindActivity(R.layout.activity_list_view)
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.img_back)
    private ImageView imgBack;

    @BindView(R.id.list_view)
    private ListView listView;

    private List<String> datas = Arrays.asList(
            "RunPriorityTestActivity",
            "BindTestActivity",
            "RouteTestActivity",
            "AdapterActivity"
    );

    protected void initView() {
        imgBack.setVisibility(View.GONE);
        listView.setAdapter(new CommonAdapter<>(this, datas, R.layout.layout_list_item,
                (holder, s, position) -> holder.setText(R.id.tv_text, s)));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                AppRouteApi.DEFAULT.navigateToRunPriorityTestActivity(this);
                break;
            case 1:
                AppRouteApi.DEFAULT.navigateToBindTestActivity(this);
                break;
            case 2:
                AppRouteApi.DEFAULT.navigateToRouteMainActivity(this);
                break;
            case 3:
                AppRouteApi.DEFAULT.navigateToAdapterActivity(this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }
}
