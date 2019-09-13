package com.fss.demo.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Context mContext;

    public static void register(Context context) {
        mContext = context;
    }

    //显示Toast信息
    public static void showToast(String message) {
        if (mContext != null) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }

}
