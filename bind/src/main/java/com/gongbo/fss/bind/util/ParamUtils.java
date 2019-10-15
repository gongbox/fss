package com.gongbo.fss.bind.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class ParamUtils {
    /**
     * 根据key值获取参数
     *
     * @param obj
     * @param key
     * @return
     */
    public static Object getParam(Object obj, String key) {
        if (obj instanceof Activity) {
            Bundle bundle = null;
            Intent intent = ((Activity) obj).getIntent();
            if (intent != null) {
                bundle = intent.getExtras();
            }
            return getParam(bundle, key);
        }
        if (obj instanceof Fragment) {
            Bundle bundle = ((Fragment) obj).getArguments();
            return getParam(bundle, key);
        }
        if (obj instanceof android.app.Fragment) {
            Bundle bundle = ((android.app.Fragment) obj).getArguments();
            return getParam(bundle, key);
        }
        throw new RuntimeException(obj.getClass().getCanonicalName() + " must be Activity or Fragment");
    }

    /**
     * 获取Bundle参数
     *
     * @param bundle
     * @param key
     * @return
     */
    private static Object getParam(Bundle bundle, String key) {
        return bundle == null ? null : bundle.get(key);
    }
}
