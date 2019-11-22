package com.fss.adapter.recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.fss.adapter.recyclerview.viewholder.DataBindingViewHolder;
import com.fss.common.kotlin.Pair;

import java.util.List;


/**
 * Created by gongbo on 2018/6/6.
 */
public class BaseBindingSingleAdapter<M, VB extends ViewDataBinding> extends RecyclerView.Adapter<DataBindingViewHolder<VB>> {

    protected List<M> mDataList;
    protected LayoutInflater mLayoutInflater;
    protected Context mContext;
    protected SparseIntArray mLayoutIds;
    protected int mBindingId;

    public static final int EMPTY_VIEW = -1;

    public BaseBindingSingleAdapter(Context context, List<M> datas, int bindingId) {
        this.mDataList = datas;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutIds = new SparseIntArray();
        this.mBindingId = bindingId;
    }

    public BaseBindingSingleAdapter(Context context, List<M> datas, int bindingId, int layoutId) {
        this(context, datas, bindingId);
        addLayout(0, layoutId);
    }

    public BaseBindingSingleAdapter(Context context, List<M> datas, int bindingId, Pair<Integer, Integer>... layoutIds) {
        this(context, datas, bindingId);
        for (Pair<Integer, Integer> pair : layoutIds) {
            addLayout(pair.first, pair.second);
        }
    }

    protected void addLayout(int type, int layoutId) {
        mLayoutIds.append(type, layoutId);
    }

    protected int getLayout(int type) {
        return mLayoutIds.get(type);
    }

    public void notifyDataSetChanged(List<M> datas) {
        this.mDataList = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataBindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VB viewDataBinding = DataBindingUtil.inflate(mLayoutInflater, getLayout(viewType), parent, false);
        return new DataBindingViewHolder<>(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataBindingViewHolder<VB> holder, int position) {
        if (getItemViewType(position) != EMPTY_VIEW) {
            onBindView(holder.getBinding(), getItem(position), position);
        }
    }

    public void onBindView(@NonNull VB viewDataBinding, M m, int position) {
        viewDataBinding.setVariable(mBindingId, m);
    }

    @Override
    public void onBindViewHolder(@NonNull DataBindingViewHolder<VB> holder, int position, @NonNull List<Object> payloads) {
        if (getItemViewType(position) != EMPTY_VIEW) {
            onBindView(holder.getBinding(), getItem(position), position, payloads);
        }
        super.onBindViewHolder(holder, position, payloads);
    }

    public void onBindView(@NonNull VB viewDataBinding, M m, int position, @NonNull List<Object> payloads) {
        viewDataBinding.setVariable(mBindingId, m);
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataList == null || mDataList.isEmpty() && getLayout(EMPTY_VIEW) != 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mDataList == null || mDataList.isEmpty() ? (getLayout(EMPTY_VIEW) == 0 ? 0 : 1) : mDataList.size();
    }

    public M getItem(int position) {
        return mDataList.get(position);
    }

}
