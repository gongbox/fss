package com.fss.bind.util;

import android.util.Log;

import com.fss.bind.FssBind;

public class FssLog {

    public final static String TAG = "FssBind";

    public static void info(String text) {
        info(TAG, text);
    }

    public static void info(String tag, String text) {
        if (FssBind.isDebug()) {
            Log.i(tag, text);
        }
    }
}
