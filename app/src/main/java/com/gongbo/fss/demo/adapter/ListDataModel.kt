package com.gongbo.fss.demo.adapter

import java.util.ArrayList

object ListDataModel {
    val datas: List<String>
        get() {
            val datas = ArrayList<String>()
            for (i in 0..49) {
                datas.add(i.toString() + "")
            }
            return datas
        }
}
