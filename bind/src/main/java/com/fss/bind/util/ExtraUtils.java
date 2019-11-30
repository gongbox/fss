package com.fss.bind.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;


import com.fss.bind.exception.NotFoundExtraException;

public class ExtraUtils {
    /**
     * 根据key值获取参数
     *
     * @param obj
     * @param key
     * @return
     */
    public static Object getExtra(Object obj, String key) throws NotFoundExtraException {
        if (obj instanceof Activity) {
            Bundle bundle = null;
            Intent intent = ((Activity) obj).getIntent();
            if (intent != null) {
                bundle = intent.getExtras();
            }
            return getExtra(bundle, key);
        }
        if (obj instanceof Fragment) {
            Bundle bundle = ((Fragment) obj).getArguments();
            return getExtra(bundle, key);
        }
        if (obj instanceof android.app.Fragment) {
            Bundle bundle = ((android.app.Fragment) obj).getArguments();
            return getExtra(bundle, key);
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
    private static Object getExtra(Bundle bundle, String key) throws NotFoundExtraException {
        if(bundle == null || !bundle.containsKey(key)){
            throw new NotFoundExtraException();
        }
        return bundle.get(key);
    }
}
