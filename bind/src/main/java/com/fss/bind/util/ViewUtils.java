package com.fss.bind.util;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;


public class ViewUtils {
    /**
     * 根据id获取view
     *
     * @param obj
     * @param id
     * @return
     */
    public static View getView(@NonNull Object obj, @IdRes int id) {
        if (obj instanceof Activity) {
            return getView((Activity) obj, id);
        }
        if (obj instanceof Fragment) {
            View view = ((Fragment) obj).getView();
            return getView(view, id);
        }
        if (obj instanceof android.support.v4.app.Fragment) {
            View view = ((android.support.v4.app.Fragment) obj).getView();
            return getView(view, id);
        }
        if (obj instanceof View) {
            return getView((View) obj, id);
        }
        throw new RuntimeException(obj.getClass().getCanonicalName() + " must be Activity or Fragment or View");
    }


    /**
     * 根据id获取view
     *
     * @param activity
     * @param id
     * @return
     */
    private static View getView(@NonNull Activity activity, @IdRes int id) {
        return activity.findViewById(id);
    }

    /**
     * 根据id获取view
     *
     * @param view
     * @param id
     * @return
     */
    public static View getView(View view, @IdRes int id) {
        return view == null ? null : view.findViewById(id);
    }
}
