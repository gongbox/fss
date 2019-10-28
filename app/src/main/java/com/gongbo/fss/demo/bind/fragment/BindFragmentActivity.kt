package com.gongbo.fss.demo.bind.fragment

import com.gongbo.fss.base.BaseFssActivity
import com.gongbo.fss.bind.annotation.BindActivity
import com.gongbo.fss.demo.R
import com.gongbo.fss.router.annotation.Route

@Route
@BindActivity(layout = R.layout.activity_bind_fragment, finish = [R.id.img_back])
class BindFragmentActivity : BaseFssActivity()
