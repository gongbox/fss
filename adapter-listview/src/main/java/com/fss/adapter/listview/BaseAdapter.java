package com.fss.adapter.listview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;


import com.fss.adapter.listview.viewholder.BaseViewHolder;
import com.fss.common.kotlin.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 * Created by $USER_NAME on 2019/2/15.
 */
public class BaseAdapter<M, VH extends BaseViewHolder> extends android.widget.BaseAdapter {
    protected List<M> mDataList;
    protected Context mContext;
    protected SparseIntArray mLayoutIds;
    private Constructor mViewHolderConstructor;
    private int mViewHolderConstructorType = 0;

    public interface OnBindViewAdapter<M, VH extends BaseViewHolder> {

        void onBindView(VH holder, M m, int position);

    }

    private OnBindViewAdapter<M, VH> onBindViewAdapter;

    public BaseAdapter(Context context, List<M> datas) {
        this.mDataList = datas;
        this.mContext = context;
        this.mLayoutIds = new SparseIntArray();
    }

    public BaseAdapter(Context context, List<M> datas, @LayoutRes int layoutId) {
        this(context, datas);
        addLayout(0, layoutId);
    }

    public BaseAdapter(Context context, List<M> datas, @LayoutRes int layoutId, OnBindViewAdapter<M, VH> onBindViewAdapter) {
        this(context, datas, layoutId);
        this.onBindViewAdapter = onBindViewAdapter;
    }

    public BaseAdapter(Context context, List<M> datas, Pair<Integer, Integer>... layoutIds) {
        this(context, datas);
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
        VH holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, getLayout(getItemViewType(position)), null);
            holder = getViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (VH) convertView.getTag();
        }
        M data = getItem(position);
        if (onBindViewAdapter != null) {
            onBindViewAdapter.onBindView(holder, data, position);
        } else {
            onBindView(holder, data, position);
        }

        return convertView;
    }

    protected VH getViewHolder(View convertView) {
        if (mViewHolderConstructor == null) {
            Class<?> viewHolderClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            try {
                Constructor constructor = viewHolderClass.getConstructor(View.class);
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                mViewHolderConstructor = constructor;
                mViewHolderConstructorType = 0;
            } catch (NoSuchMethodException e) {
                try {
                    Constructor constructor = viewHolderClass.getConstructor(this.getClass(), View.class);
                    if (!constructor.isAccessible()) {
                        constructor.setAccessible(true);
                    }
                    mViewHolderConstructor = constructor;
                    mViewHolderConstructorType = 1;
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (mViewHolderConstructor != null) {
            try {
                if (mViewHolderConstructorType == 0) {
                    return (VH) mViewHolderConstructor.newInstance(convertView);
                } else {
                    return (VH) mViewHolderConstructor.newInstance(this, convertView);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Cann't get ViewHolder in class:" + this.getClass().getCanonicalName());
        }
        throw new RuntimeException("Cann't instance ViewHolder in class:" + this.getClass().getCanonicalName());
    }

    protected void onBindView(VH holder, M m, int position) {
    }
}
