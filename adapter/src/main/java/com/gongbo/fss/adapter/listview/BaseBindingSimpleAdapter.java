package com.gongbo.fss.adapter.listview;

import android.content.Context;

import androidx.databinding.ViewDataBinding;


import com.gongbo.fss.common.kotlin.Pair;

import java.util.List;


/**
 * Created by gongbo on 2018/5/21.
 * 列表视图适配器
 */
public abstract class BaseBindingSimpleAdapter<M> extends BaseBindingSimpleSingleAdapter<M, ViewDataBinding> {

    public BaseBindingSimpleAdapter(Context context, List<M> datas, int bindingId) {
        super(context, datas, bindingId);
    }

    public BaseBindingSimpleAdapter(Context context, List<M> datas, int bindingId, int layoutId) {
        super(context, datas, bindingId, layoutId);
    }

    public BaseBindingSimpleAdapter(Context context, List<M> datas, int bindingId, Pair<Integer, Integer>... layoutIds) {
        super(context, datas, bindingId, layoutIds);
    }
}
