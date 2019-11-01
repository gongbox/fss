package com.gongbo.fss.adapter.recyclerview.viewholder;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.gongbo.fss.bind.FssBind;


/**
 * Created by $USER_NAME on 2019/2/16.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        FssBind.bindView(this, BaseViewHolder.class, itemView);
    }

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        return itemView.findViewById(id);
    }
}
