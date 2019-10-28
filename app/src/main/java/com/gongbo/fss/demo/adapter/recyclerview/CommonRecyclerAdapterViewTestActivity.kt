package com.gongbo.fss.demo.adapter.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.gongbo.fss.adapter.recyclerview.CommonRecyclerAdapter
import com.gongbo.fss.adapter.recyclerview.viewholder.CommonViewHolder
import com.gongbo.fss.base.BaseFssActivity
import com.gongbo.fss.bind.annotation.BindActivity
import com.gongbo.fss.bind.annotation.BindView
import com.gongbo.fss.demo.R
import com.gongbo.fss.demo.adapter.ListDataModel
import com.gongbo.fss.router.annotation.Route

@Route(group = "recyclerView")
@BindActivity(layout = R.layout.activity_recycler_view, finish = [R.id.img_back])
class CommonRecyclerAdapterViewTestActivity : BaseFssActivity() {

    @BindView(id = R.id.recycler_view)
    private val recyclerView: RecyclerView? = null

    override fun initView() {
        super.initView()
        recyclerView!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = object : CommonRecyclerAdapter<String>(this, ListDataModel.datas, R.layout.layout_list_item) {
            override fun onBindView(holder: CommonViewHolder, s: String, position: Int) {
                super.onBindView(holder, s, position)
                holder.setText(R.id.tv_text, s)
            }
        }
    }
}
