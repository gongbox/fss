package com.gongbo.fss.demo.adapter

import android.view.View
import android.widget.AdapterView
import android.widget.ListView

import com.gongbo.fss.adapter.listview.CommonAdapter
import com.gongbo.fss.base.BaseFssActivity
import com.gongbo.fss.bind.annotation.BindActivity
import com.gongbo.fss.bind.annotation.BindView
import com.gongbo.fss.demo.R
import com.gongbo.fss.router.annotation.Route

import java.util.Arrays

import com.gongbo.fss.router.FssRouteApi.ListViewRouteApi
import com.gongbo.fss.router.FssRouteApi.RecyclerViewRouteApi


@Route
@BindActivity(layout = R.layout.activity_adapter, finish = [R.id.img_back])
class AdapterActivity : BaseFssActivity(), AdapterView.OnItemClickListener {

    @BindView(id = R.id.list_view)
    private val listView: ListView? = null

    private val datas = Arrays.asList(
            "BaseAdapter",
            "BaseBindAdapter",
            "BaseBindingAdapter",
            "BaseBindingSingleAdapter",
            "CommonAdapter",
            "BaseAdapter",
            "BaseBindRecyclerViewAdapter",
            "BaseBindingAdapter",
            "CommonRecyclerAdapter"
    )

    override fun initView() {
        super.initView()
        listView!!.adapter = CommonAdapter(this, datas, R.layout.layout_list_item
        ) { holder, s, _ -> holder.setText(R.id.tv_text, s) }
        listView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        when (position) {
            0 -> ListViewRouteApi.navigateToBaseAdapterTestActivity(this)
            1 -> ListViewRouteApi.navigateToBaseBindAdapterTestActivity(this)
            2 -> ListViewRouteApi.navigateToBaseBindingAdapterTestActivity(this)
            3 -> ListViewRouteApi.navigateToBaseBindingSingleAdapterTestActivity(this)
            4 -> ListViewRouteApi.navigateToCommonAdapterTestActivity(this)
            5 -> RecyclerViewRouteApi.navigateToBaseRecyclerViewAdapterTestActivity(this)
            6 -> RecyclerViewRouteApi.navigateToBaseBindRecyclerViewAdapterTestActivity(this)
            7 -> RecyclerViewRouteApi.navigateToBaseBindingRecyclerViewAdapterTestActivity(this)
            8 -> RecyclerViewRouteApi.navigateToCommonRecyclerAdapterViewTestActivity(this)
            else -> throw IllegalStateException("Unexpected value: $position")
        }
    }

}

