package com.gongbo.fss.demo;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.gongbo.fss.adapter.listview.CommonAdapter;
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

    @BindView(id = R.id.img_back)
    private ImageView imgBack;

    @BindView(id = R.id.list_view)
    private ListView listView;

    private List<String> datas = Arrays.asList(
            "RunPriorityTestActivity",
            "BindTestActivity",
            "RouteTestActivity",
            "AdapterActivity"
    );

    @Override
    protected void initView() {
        super.initView();
        imgBack.setVisibility(View.GONE);
        listView.setAdapter(new CommonAdapter<>(this, datas, R.layout.layout_list_item,
                (holder, s, position) -> holder.setText(R.id.tv_text, s)));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                DefaultRouteApi.navigateToRunPriorityTestActivity(this);
                break;
            case 1:
                DefaultRouteApi.navigateToBindTestActivity(this);
                break;
            case 2:
                DefaultRouteApi.navigateToRouteMainActivity(this);
                break;
            case 3:
                DefaultRouteApi.navigateToAdapterActivity(this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }
}
