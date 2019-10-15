package com.gongbo.fss.adapter.recyclerview;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.gongbo.fss.adapter.recyclerview.viewholder.CommonViewHolder;

import java.util.List;

/**
 * Created by $USER_NAME on 2019/2/15.
 */
public abstract class CommonRecyclerAdapter<M> extends BaseAdapter<M, CommonViewHolder> {


    public CommonRecyclerAdapter(Context context, List<M> datas) {
        super(context, datas);
    }

    public CommonRecyclerAdapter(Context context, List<M> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    public CommonRecyclerAdapter(Context context, List<M> datas, Pair<Integer, Integer>... layoutIds) {
        super(context, datas, layoutIds);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(getLayout(viewType), parent, false);
        return new CommonViewHolder(view);
    }

}
