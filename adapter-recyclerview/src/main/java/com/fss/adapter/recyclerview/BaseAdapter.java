package com.fss.adapter.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Pair;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by $USER_NAME on 2019/2/15.
 */
public class BaseAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<M> mDataList;
    protected LayoutInflater mLayoutInflater;
    protected Context mContext;
    protected SparseIntArray mLayoutIds;
    private Constructor mViewHolderConstructor;
    private int mViewHolderConstructorType;

    public static final int EMPTY_VIEW = -1;

    public interface OnBindViewAdapter<M, VH extends RecyclerView.ViewHolder> {

        void onBindView(VH holder, M m, int position);

    }

    private OnBindViewAdapter<M, VH> onBindViewAdapter;

    public BaseAdapter(Context context, List<M> datas) {
        this.mDataList = datas;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutIds = new SparseIntArray();
    }

    public BaseAdapter(Context context, List<M> datas, int layoutId) {
        this(context, datas);
        addLayout(0, layoutId);
    }

    public BaseAdapter(Context context, List<M> datas, int layoutId, OnBindViewAdapter<M, VH> onBindViewAdapter) {
        this(context, datas, layoutId);
        this.onBindViewAdapter = onBindViewAdapter;
    }

    public BaseAdapter(Context context, List<M> datas, Pair<Integer, Integer>... layoutIds) {
        this(context, datas);
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
        this.mDataList = datas;
        notifyDataSetChanged();
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

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (getItemViewType(position) != EMPTY_VIEW) {
            if(onBindViewAdapter != null){
                onBindViewAdapter.onBindView(holder, getItem(position), position);
            }else {
                onBindView(holder, getItem(position), position);
            }
        }
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(getLayout(viewType), parent, false);

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
                    return (VH) mViewHolderConstructor.newInstance(view);
                } else {
                    return (VH) mViewHolderConstructor.newInstance(this, view);
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

    public void onBindView(@NonNull VH holder, M m, int position) {
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> payloads) {
        onBindView(holder, getItem(position), position, payloads);
        super.onBindViewHolder(holder, position, payloads);
    }

    public void onBindView(@NonNull VH holder, M m, int position, @NonNull List<Object> payloads) {
    }
}

