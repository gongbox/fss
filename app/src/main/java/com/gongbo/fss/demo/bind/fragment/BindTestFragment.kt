package com.gongbo.fss.demo.bind.fragment


import com.gongbo.fss.base.BaseFssFragment
import com.gongbo.fss.bind.annotation.BindFragment
import com.gongbo.fss.demo.R

import com.gongbo.fss.demo.util.ToastUtils.showToast

@BindFragment(layout = R.layout.fragment_bind_test)
class BindTestFragment : BaseFssFragment() {

    override fun initView() {
        showToast("hello")
    }
}
