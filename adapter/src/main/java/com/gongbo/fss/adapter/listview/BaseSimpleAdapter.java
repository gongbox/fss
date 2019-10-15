package com.gongbo.fss.adapter.listview;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.LayoutRes;

import com.gongbo.fss.adapter.listview.viewholder.BaseViewHolder;
import com.gongbo.fss.common.kotlin.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 * Created by $USER_NAME on 2019/2/15.
 */
public abstract class BaseSimpleAdapter<M, VH extends BaseViewHolder> extends BaseAdapter {
    protected List<M> mDatas;
    protected Context mContext;
    protected SparseIntArray mLayoutIds;

    public BaseSimpleAdapter(Context context, List<M> datas) {
        this.mDatas = datas;
        this.mContext = context;
        this.mLayoutIds = new SparseIntArray();
    }

    public BaseSimpleAdapter(Context context, List<M> datas, @LayoutRes int layoutId) {
        this(context, datas);
        addLayout(0, layoutId);
    }

    public BaseSimpleAdapter(Context context, List<M> datas, Pair<Integer, Integer>... layoutIds) {
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

    public void updateData(List<M> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public M getItem(int position) {
        return mDatas.get(position);
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
        onBindView(holder, data, position);

        return convertView;
    }

    private Constructor mViewHolderConstructor;

    protected VH getViewHolder(View convertView) {
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
        }

        throw new RuntimeException("Cann't get ViewHolder in class:" + this.getClass().getCanonicalName());
    }

    protected abstract void onBindView(VH holder, M m, int position);

}
