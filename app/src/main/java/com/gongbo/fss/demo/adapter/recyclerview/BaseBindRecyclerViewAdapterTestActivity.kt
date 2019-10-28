package com.gongbo.fss.demo.adapter.recyclerview

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.gongbo.fss.adapter.recyclerview.BaseAdapter
import com.gongbo.fss.adapter.recyclerview.viewholder.BaseViewHolder
import com.gongbo.fss.base.BaseFssActivity
import com.gongbo.fss.bind.annotation.BindActivity
import com.gongbo.fss.bind.annotation.BindView
import com.gongbo.fss.demo.R
import com.gongbo.fss.demo.adapter.ListDataModel
import com.gongbo.fss.router.annotation.Route

@Route(group = "recyclerView")
@BindActivity(layout = R.layout.activity_recycler_view, finish = [R.id.img_back])
class BaseBindRecyclerViewAdapterTestActivity : BaseFssActivity() {

    @BindView(id = R.id.recycler_view)
    private val recyclerView: RecyclerView? = null

    override fun initView() {
        super.initView()
        recyclerView!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = BindAdapter(this, ListDataModel.datas)
    }

    internal class BindAdapter(context: Context, datas: List<String>) : BaseAdapter<String, BindAdapter.BindViewHolder>(context, datas, R.layout.layout_list_item) {

        override fun onBindView(holder: BindViewHolder, s: String, position: Int) {
            super.onBindView(holder, s, position)
            holder.tvText!!.text = s
        }

        internal class BindViewHolder(view: View) : BaseViewHolder(view) {
            @BindView(id = R.id.tv_text)
            var tvText: TextView? = null
        }
    }
}

