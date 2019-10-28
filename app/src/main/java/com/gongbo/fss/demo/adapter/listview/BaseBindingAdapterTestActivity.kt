package com.gongbo.fss.demo.adapter.listview

import android.widget.ListView

import com.gongbo.fss.adapter.listview.BaseBindingAdapter
import com.gongbo.fss.base.BaseFssActivity
import com.gongbo.fss.bind.annotation.BindActivity
import com.gongbo.fss.bind.annotation.BindView
import com.gongbo.fss.demo.R
import com.gongbo.fss.demo.adapter.ListDataModel
import com.gongbo.fss.router.annotation.Route

@Route(group = "listView")
@BindActivity(layout = R.layout.activity_list_view, finish = [R.id.img_back])
class BaseBindingAdapterTestActivity : BaseFssActivity() {

    @BindView(id = R.id.list_view)
    private val listView: ListView? = null

    override fun initView() {
        super.initView()
        listView!!.adapter = BaseBindingAdapter<String>(
                this,
                ListDataModel.datas,
                com.gongbo.fss.demo.BR.value,
                R.layout.layout_binding_list_item)
    }
}



