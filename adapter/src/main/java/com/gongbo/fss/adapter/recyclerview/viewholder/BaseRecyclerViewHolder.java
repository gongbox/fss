package com.gongbo.fss.adapter.recyclerview.viewholder;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.gongbo.fss.bind.FssBind;


/**
 * Created by $USER_NAME on 2019/2/16.
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    public BaseRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        FssBind.bindView(this, BaseRecyclerViewHolder.class, itemView);
    }

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        return itemView.findViewById(id);
    }
}
