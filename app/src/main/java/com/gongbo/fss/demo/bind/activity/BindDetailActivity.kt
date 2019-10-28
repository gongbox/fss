package com.gongbo.fss.demo.bind.activity

import com.gongbo.fss.base.BaseBindingFssActivity
import com.gongbo.fss.bind.annotation.BindActivity
import com.gongbo.fss.bind.annotation.BindExtra
import com.gongbo.fss.bind.annotation.BindOnClick
import com.gongbo.fss.demo.BR
import com.gongbo.fss.demo.R
import com.gongbo.fss.demo.databinding.ActivityBindDetailBinding
import com.gongbo.fss.router.annotation.Route

import com.gongbo.fss.demo.util.ToastUtils.showToast


@Route
@BindActivity(layout = R.layout.activity_bind_detail, finish = [R.id.img_back])
@BindExtra(name = "value", bindingName = "value1")
class BindDetailActivity : BaseBindingFssActivity<ActivityBindDetailBinding>() {

    @BindExtra(bindingName = "value2")
    private val value2: String? = null

    @BindExtra
    private val value3: Int? = null

    @BindOnClick(id = [R.id.btn_show_value])
    private fun onClick() {
        showToast(value3!!.toString() + "")
    }
}
