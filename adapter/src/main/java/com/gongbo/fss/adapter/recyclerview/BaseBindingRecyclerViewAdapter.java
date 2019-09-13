package com.gongbo.fss.adapter.recyclerview;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.gongbo.fss.common.kotlin.Pair;

import java.util.List;


/**
 * Created by gongbo on 2018/6/6.
 */
public class BaseBindingRecyclerViewAdapter<M> extends BaseBindingRecyclerViewSingleAdapter<M, ViewDataBinding> {

    public BaseBindingRecyclerViewAdapter(Context context, List<M> datas, int bindingId) {
        super(context, datas, bindingId);
    }

    public BaseBindingRecyclerViewAdapter(Context context, List<M> datas, int bindingId, int layoutId) {
        super(context, datas, bindingId, layoutId);
    }

    public BaseBindingRecyclerViewAdapter(Context context, List<M> datas, int bindingId, Pair<Integer, Integer>... layoutIds) {
        super(context, datas, bindingId, layoutIds);
    }
}
