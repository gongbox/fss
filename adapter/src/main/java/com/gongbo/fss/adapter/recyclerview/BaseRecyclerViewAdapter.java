package com.gongbo.fss.adapter.recyclerview;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gongbo.fss.common.kotlin.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by $USER_NAME on 2019/2/15.
 */
public abstract class BaseRecyclerViewAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<M> mDatas;
    protected LayoutInflater mLayoutInflater;
    protected Context mContext;
    protected SparseIntArray mLayoutIds;

    public static final int EMPTY_VIEW = -1;

    public BaseRecyclerViewAdapter(Context context, List<M> datas) {
        this.mDatas = datas;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutIds = new SparseIntArray();
    }

    public BaseRecyclerViewAdapter(Context context, List<M> datas, int layoutId) {
        this(context, datas);
        addLayout(0, layoutId);
    }

    public BaseRecyclerViewAdapter(Context context, List<M> datas, Pair<Integer, Integer>... layoutIds) {
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
        this.mDatas = datas;
        notifyDataSetChanged();
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

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (getItemViewType(position) != EMPTY_VIEW) {
            onBindView(holder, getItem(position), position);
        }
    }

    private Constructor mViewHolderConstructor;

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
            } catch (NoSuchMethodException e) {
                try {
                    Constructor constructor = viewHolderClass.getConstructor(this.getClass(), View.class);
                    if (!constructor.isAccessible()) {
                        constructor.setAccessible(true);
                    }
                    mViewHolderConstructor = constructor;
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (mViewHolderConstructor != null) {

            try {
                if (mViewHolderConstructor.getParameterTypes().length == 1) {
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
        }

        throw new RuntimeException("Cann't get ViewHolder in class:" + this.getClass().getCanonicalName());
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

