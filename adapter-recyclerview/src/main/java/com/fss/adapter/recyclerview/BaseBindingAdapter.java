package com.fss.adapter.recyclerview;

import android.content.Context;
import androidx.databinding.ViewDataBinding;


import com.fss.common.kotlin.Pair;

import java.util.List;


/**
 * Created by gongbo on 2018/6/6.
 */
public class BaseBindingAdapter<M> extends BaseBindingSingleAdapter<M, ViewDataBinding> {

    public BaseBindingAdapter(Context context, List<M> datas, int bindingId) {
        super(context, datas, bindingId);
    }

    public BaseBindingAdapter(Context context, List<M> datas, int bindingId, int layoutId) {
        super(context, datas, bindingId, layoutId);
    }

    public BaseBindingAdapter(Context context, List<M> datas, int bindingId, Pair<Integer, Integer>... layoutIds) {
        super(context, datas, bindingId, layoutIds);
    }
}
