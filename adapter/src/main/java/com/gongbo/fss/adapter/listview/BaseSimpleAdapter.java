package com.gongbo.fss.adapter.listview;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.LayoutRes;

import com.gongbo.fss.common.kotlin.Pair;
import com.gongbo.fss.adapter.listview.viewholder.BaseViewHolder;

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

    protected ListView mListView;

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
        setView(holder, data, position);

        return convertView;
    }

    protected VH getViewHolder(View convertView) {
        //获取第二个范型参数的类型，该类型即为ViewHolder类型
        Class<?> viewHolderClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

        try {
            Constructor constructor = viewHolderClass.getConstructor(View.class);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return (VH) constructor.newInstance(convertView);
        } catch (NoSuchMethodException e) {
            try {
                Constructor constructor = viewHolderClass.getConstructor(this.getClass(), View.class);
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                return (VH) constructor.newInstance(this, convertView);
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Cann't get ViewHolder in class:" + this.getClass().getCanonicalName());
    }

    protected abstract void setView(VH holder, M m, int position);

    /**
     * 使能高度自适应
     *
     * @param mListView
     * @param status
     */
    public void enableAutoHeight(ListView mListView, boolean status) {
        if (status) {
            this.mListView = mListView;
        } else {
            this.mListView = null;
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (mListView != null) {
            updateListViewHeightBasedOnChildren();
        }
    }

    /**
     * 自适应高度
     */
    public void updateListViewHeightBasedOnChildren() {
        int totalHeight = 0;

        for (int i = 0; i < this.getCount(); i++) {
            View listItem = this.getView(i, null, mListView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = mListView.getLayoutParams();

        params.height = totalHeight
                + (mListView.getDividerHeight() * (this.getCount() - 1));

        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10); // 可删除

        mListView.setLayoutParams(params);
    }

    /**
     * 更新单个数据
     * @param listView
     * @param position
     */
    public void notifyItemChanged(ListView listView, int position) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int lastVisiblePosition = listView.getLastVisiblePosition();

        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            View view = listView.getChildAt(position - firstVisiblePosition);
            getView(position, view, listView);
        }
    }
}
