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
class BaseAdapterTestActivity : BaseFssActivity() {

    @BindView(id = R.id.list_view)
    private val listView: ListView? = null

    override fun initView() {
        super.initView()

        listView!!.adapter = Adapter(this, ListDataModel.datas)
    }

    internal class Adapter(context: Context, datas: List<String>) : BaseAdapter<String, Adapter.ViewHolder>(context, datas, R.layout.layout_list_item) {

        override fun onBindView(holder: ViewHolder, s: String, position: Int) {
            holder.tvText!!.text = s
        }

        internal class ViewHolder(view: View) : BaseViewHolder(view) {
            var tvText: TextView? = null

            init {
                this.tvText = findViewById(R.id.tv_text)
            }
        }
    }
}



