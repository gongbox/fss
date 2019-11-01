package com.gongbo.fss.adapter.listview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gongbo.fss.common.kotlin.Pair;

import java.util.List;


/**
 * Created by gongbo on 2018/5/21.
 * 列表视图适配器
 */
public class BaseBindingSingleAdapter<M, VB extends ViewDataBinding> extends BaseAdapter {

    protected List<M> mDataList;
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected SparseIntArray mLayoutIds;
    protected int mBindingId;

    public BaseBindingSingleAdapter(Context context, List<M> datas, int bindingId) {
        this.mDataList = datas;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutIds = new SparseIntArray();
        this.mBindingId = bindingId;
    }

    public BaseBindingSingleAdapter(Context context, List<M> datas, int bindingId, @LayoutRes int layoutId) {
        this(context, datas, bindingId);
        addLayout(0, layoutId);
    }

    public BaseBindingSingleAdapter(Context context, List<M> datas, int bindingId, Pair<Integer, Integer>... layoutIds) {
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

    public void notifyDataSetChanged(List<M> datas) {
        this.mDataList = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public M getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VB viewDataBinding = null;
        if (convertView == null) {
            int layoutId = getLayout(getItemViewType(position));
            viewDataBinding = DataBindingUtil.inflate(mLayoutInflater, layoutId, parent, false);
            convertView = viewDataBinding.getRoot();

            if (convertView == null) {
                throw new RuntimeException("Not found layoutId!");
            }

            convertView.setTag(viewDataBinding);
        } else {
            viewDataBinding = (VB) convertView.getTag();
        }
        M data = getItem(position);
        onBindView(viewDataBinding, data, position);
        return convertView;
    }

    protected void onBindView(VB viewDataBinding, M m, int position) {
        viewDataBinding.setVariable(mBindingId, m);
    }

}
