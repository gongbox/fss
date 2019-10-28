package com.gongbo.fss.demo

import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView

import com.gongbo.fss.adapter.listview.CommonAdapter
import com.gongbo.fss.base.BaseFssActivity
import com.gongbo.fss.bind.annotation.BindActivity
import com.gongbo.fss.bind.annotation.BindView
import com.gongbo.fss.router.annotation.Route

import java.util.Arrays

import com.gongbo.fss.router.FssRouteApi.DefaultRouteApi

@Route
@BindActivity(layout = R.layout.activity_list_view)
class MainActivity : BaseFssActivity(), AdapterView.OnItemClickListener {

    @BindView(id = R.id.img_back)
    private val imgBack: ImageView? = null

    @BindView(id = R.id.list_view)
    private val listView: ListView? = null

    private val datas = Arrays.asList(
            "RunPriorityTestActivity",
            "BindTestActivity",
            "RouteTestActivity",
            "AdapterActivity"
    )

    override fun initView() {
        super.initView()
        imgBack!!.visibility = View.GONE
        listView!!.adapter = CommonAdapter(this, datas, R.layout.layout_list_item
        ) { holder, s, _ -> holder.setText(R.id.tv_text, s) }
        listView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        when (position) {
            0 -> DefaultRouteApi.navigateToRunPriorityTestActivity(this)
            1 -> DefaultRouteApi.navigateToBindTestActivity(this)
            2 -> DefaultRouteApi.navigateToRouteMainActivity(this)
            3 -> DefaultRouteApi.navigateToAdapterActivity(this)
            else -> throw IllegalStateException("Unexpected value: $position")
        }
    }
}
