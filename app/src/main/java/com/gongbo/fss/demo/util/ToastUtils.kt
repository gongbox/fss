package com.gongbo.fss.demo.util

import android.content.Context
import android.widget.Toast

object ToastUtils {

    private var mContext: Context? = null

    fun register(context: Context) {
        mContext = context
    }

    //显示Toast信息
    fun showToast(message: String) {
        if (mContext != null) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }
    }

}
