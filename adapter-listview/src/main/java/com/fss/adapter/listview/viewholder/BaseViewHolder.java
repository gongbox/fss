package com.fss.adapter.listview.viewholder;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;


import com.fss.bind.FssBind;


/**
 * Created by $USER_NAME on 2019/2/16.
 */
public class BaseViewHolder {

    protected View itemView;

    public BaseViewHolder(View itemView) {
        this.itemView = itemView;
        FssBind.bindView(this, BaseViewHolder.class, itemView);
    }

    public View getItemView() {
        return itemView;
    }

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        return itemView.findViewById(id);
    }
}
