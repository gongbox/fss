package com.gongbo.fss.adapter.recyclerview;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


import com.gongbo.fss.common.kotlin.Pair;
import com.gongbo.fss.adapter.recyclerview.viewholder.BindingViewHolder;

import java.util.List;


/**
 * Created by gongbo on 2018/6/6.
 */
public class BaseBindingRecyclerViewSingleAdapter<M, VB extends ViewDataBinding> extends RecyclerView.Adapter<BindingViewHolder<VB>> {

    protected List<M> mDatas;
    protected LayoutInflater mLayoutInflater;
    protected Context mContext;
    protected SparseIntArray mLayoutIds;
    protected int mBindingId;

    public static final int EMPTY_VIEW = -1;

    public BaseBindingRecyclerViewSingleAdapter(Context context, List<M> datas, int bindingId) {
        this.mDatas = datas;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutIds = new SparseIntArray();
        this.mBindingId = bindingId;
    }

    public BaseBindingRecyclerViewSingleAdapter(Context context, List<M> datas, int bindingId, int layoutId) {
        this(context, datas, bindingId);
        addLayout(0, layoutId);
    }

    public BaseBindingRecyclerViewSingleAdapter(Context context, List<M> datas, int bindingId, Pair<Integer, Integer>... layoutIds) {
        this(context, datas, bindingId);
        for (Pair<Integer, Integer> pair : layoutIds) {
            addLayout(pair.first, pair.second);
        }
    }

    public void addLayout(int type, int layoutId) {
        mLayoutIds.append(type, layoutId);
    }

    public int getLayout(int type) {
        return mLayoutIds.get(type);
    }

    public void updateData(List<M> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VB viewDataBinding = DataBindingUtil.inflate(mLayoutInflater, getLayout(viewType), parent, false);
        return new BindingViewHolder<>(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<VB> holder, int position) {
        if (getItemViewType(position) != EMPTY_VIEW) {
            onBindView(holder.getBinding(), getItem(position), position);
        }
    }

    public void onBindView(@NonNull VB viewDataBinding, M m, int position) {
        viewDataBinding.setVariable(mBindingId, m);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<VB> holder, int position, @NonNull List<Object> payloads) {
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
        if (mDatas == null || mDatas.isEmpty() && getLayout(EMPTY_VIEW) != 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null || mDatas.isEmpty() ? (getLayout(EMPTY_VIEW) == 0 ? 0 : 1) : mDatas.size();
    }

    public M getItem(int position) {
        return mDatas.get(position);
    }

}
