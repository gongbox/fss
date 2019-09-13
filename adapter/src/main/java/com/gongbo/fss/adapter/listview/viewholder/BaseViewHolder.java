package com.gongbo.fss.adapter.listview.viewholder;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import com.gongbo.fss.bind.FssBind;


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
