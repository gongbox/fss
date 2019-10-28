package com.gongbo.fss.demo.adapter.listview

import android.content.Context
import android.view.View
import android.widget.ListView
import android.widget.TextView

import com.gongbo.fss.adapter.listview.BaseAdapter
import com.gongbo.fss.adapter.listview.viewholder.BaseViewHolder
import com.gongbo.fss.base.BaseFssActivity
import com.gongbo.fss.bind.annotation.BindActivity
import com.gongbo.fss.bind.annotation.BindView
import com.gongbo.fss.demo.R
import com.gongbo.fss.demo.adapter.ListDataModel
import com.gongbo.fss.router.annotation.Route

@Route(group = "listView")
@BindActivity(layout = R.layout.activity_list_view, finish = [R.id.img_back])
class BaseBindAdapterTestActivity : BaseFssActivity() {

    @BindView(id = R.id.list_view)
    private val listView: ListView? = null

    override fun initView() {
        super.initView()
        listView!!.adapter = BindAdapter(this, ListDataModel.datas)
    }

    internal class BindAdapter(context: Context, datas: List<String>) : BaseAdapter<String, BindAdapter.BindViewHolder>(context, datas, R.layout.layout_list_item) {

        override fun onBindView(holder: BindViewHolder, s: String, position: Int) {
            holder.tvText!!.text = s
        }

        internal inner class BindViewHolder(itemView: View) : BaseViewHolder(itemView) {
            @BindView(id = R.id.tv_text)
            var tvText: TextView? = null
        }
    }
}



