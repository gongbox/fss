package com.gongbo.fss.demo.route;

import android.widget.ListView;

import com.gongbo.fss.adapter.listview.CommonAdapter;
import com.gongbo.fss.base.BaseFssActivity;
import com.gongbo.fss.bind.annotation.BindActivity;
import com.gongbo.fss.bind.annotation.BindExtra;
import com.gongbo.fss.bind.annotation.BindView;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.route.extra.ParcelableType;
import com.gongbo.fss.demo.route.extra.SerializableType;
import com.gongbo.fss.router.annotation.DefaultExtra;
import com.gongbo.fss.router.annotation.Route;
import com.gongbo.fss.router.annotation.RouteExtra;

import java.util.Arrays;
import java.util.List;

@Route(routeExtras = {
        @RouteExtra(name = "booleanValue", type = boolean.class),
        @RouteExtra(name = "byteValue", type = byte.class),
        @RouteExtra(name = "charValue", type = char.class),
        @RouteExtra(name = "shortValue", type = short.class),
        @RouteExtra(name = "intValue", type = int.class),
        @RouteExtra(name = "longValue", type = long.class),
        @RouteExtra(name = "floatValue", type = float.class),
        @RouteExtra(name = "doubleValue", type = double.class),
        @RouteExtra(name = "stringValue", type = String.class),
        @RouteExtra(name = "serializableValue", type = SerializableType.class),
        @RouteExtra(name = "parcelableValue", type = ParcelableType.class),
})
//默认参数
@Route(routeExtras = {
        @RouteExtra(name = "serializableValue", type = SerializableType.class),
        @RouteExtra(name = "parcelableValue", type = ParcelableType.class),
}, defaultExtras = {
        @DefaultExtra(name = "booleanValue", defaultValue = "true", type = boolean.class),
        @DefaultExtra(name = "byteValue", defaultValue = "12", type = byte.class),
        @DefaultExtra(name = "charValue", defaultValue = "c", type = char.class),
        @DefaultExtra(name = "shortValue", defaultValue = "123", type = short.class),
        @DefaultExtra(name = "intValue", defaultValue = "1234", type = int.class),
        @DefaultExtra(name = "longValue", defaultValue = "12345", type = long.class),
        @DefaultExtra(name = "floatValue", defaultValue = "1.01", type = float.class),
        @DefaultExtra(name = "doubleValue", defaultValue = "1.23", type = double.class),
        @DefaultExtra(name = "stringValue", defaultValue = "hello", type = String.class),
})
@BindActivity(value = R.layout.activity_route_extra_test, finishViewId = R.id.img_back)
public class RouteExtraTestActivity extends BaseFssActivity {
    @BindExtra
    private boolean booleanValue;
    @BindExtra
    private byte byteValue;
    @BindExtra
    private char charValue;
    @BindExtra
    private short shortValue;
    @BindExtra
    private int intValue;
    @BindExtra
    private long longValue;
    @BindExtra
    private float floatValue;
    @BindExtra
    private double doubleValue;
    @BindExtra
    private String stringValue;
    @BindExtra
    private SerializableType serializableValue;
    @BindExtra
    private ParcelableType parcelableValue;

    @BindView(R.id.list_view)
    private ListView listView;

    private List<String> datas = Arrays.asList(
            "booleanValue",
            "byteValue",
            "charValue",
            "shortValue",
            "intValue",
            "longValue",
            "floatValue",
            "doubleValue",
            "stringValue",
            "serializableValue",
            "parcelableValue"
    );

    protected void initView() {
        listView.setAdapter(new CommonAdapter<>(this, datas, R.layout.layout_list_item,
                (holder, s, position) -> {
                    switch (s) {
                        case "booleanValue":
                            holder.setText(R.id.tv_text, booleanValue + "");
                            break;
                        case "byteValue":
                            holder.setText(R.id.tv_text, byteValue + "");
                            break;
                        case "charValue":
                            holder.setText(R.id.tv_text, charValue + "");
                            break;
                        case "shortValue":
                            holder.setText(R.id.tv_text, shortValue + "");
                            break;
                        case "intValue":
                            holder.setText(R.id.tv_text, intValue + "");
                            break;
                        case "longValue":
                            holder.setText(R.id.tv_text, longValue + "");
                            break;
                        case "floatValue":
                            holder.setText(R.id.tv_text, floatValue + "");
                            break;
                        case "doubleValue":
                            holder.setText(R.id.tv_text, doubleValue + "");
                            break;
                        case "stringValue":
                            holder.setText(R.id.tv_text, stringValue + "");
                            break;
                        case "serializableValue":
                            holder.setText(R.id.tv_text, serializableValue + "");
                            break;
                        case "parcelableValue":
                            holder.setText(R.id.tv_text, parcelableValue + "");
                            break;
                    }
                }));
    }

}
