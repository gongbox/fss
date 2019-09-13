package com.gongbo.fss.adapter.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


import com.gongbo.fss.common.kotlin.Pair;
import com.gongbo.fss.adapter.recyclerview.viewholder.RecyclerViewCommonViewHolder;

import java.util.List;

/**
 * Created by $USER_NAME on 2019/2/15.
 */
public abstract class CommonRecyclerViewAdapter<M> extends BaseRecyclerViewAdapter<M, RecyclerViewCommonViewHolder> {


    public CommonRecyclerViewAdapter(Context context, List<M> datas) {
        super(context, datas);
    }

    public CommonRecyclerViewAdapter(Context context, List<M> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    public CommonRecyclerViewAdapter(Context context, List<M> datas, Pair<Integer, Integer>... layoutIds) {
        super(context, datas, layoutIds);
    }

    @NonNull
    @Override
    public RecyclerViewCommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(getLayout(viewType), parent, false);
        return new RecyclerViewCommonViewHolder(view);
    }

}
