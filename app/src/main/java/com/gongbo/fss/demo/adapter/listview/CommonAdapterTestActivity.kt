package com.gongbo.fss.demo.adapter.listview

import android.widget.ListView
import android.widget.TextView

import com.gongbo.fss.adapter.listview.CommonAdapter
import com.gongbo.fss.adapter.listview.viewholder.CommonViewHolder
import com.gongbo.fss.base.BaseFssActivity
import com.gongbo.fss.bind.annotation.BindActivity
import com.gongbo.fss.bind.annotation.BindView
import com.gongbo.fss.demo.R
import com.gongbo.fss.demo.adapter.ListDataModel
import com.gongbo.fss.router.annotation.Route

@Route(group = "listView")
@BindActivity(layout = R.layout.activity_list_view, finish = [R.id.img_back])
class CommonAdapterTestActivity : BaseFssActivity() {

    @BindView(id = R.id.list_view)
    private val listView: ListView? = null

    override fun initView() {
        super.initView()
        listView!!.adapter = object : CommonAdapter<String>(this, ListDataModel.datas, R.layout.layout_list_item) {
            override fun onBindView(holder: CommonViewHolder, str: String, position: Int) {
                val textView = holder.getView<TextView>(R.id.tv_text)
                textView.text = str
                //可以用holder.setText(R.id.tv_text, str);代替
            }
        }
    }
}
