package com.gongbo.fss.adapter.listview;

import android.content.Context;
import android.view.View;

import com.gongbo.fss.adapter.listview.viewholder.CommonViewHolder;
import com.gongbo.fss.common.kotlin.Pair;

import java.util.List;

/**
 * Created by $USER_NAME on 2019/2/15.
 */
public abstract class CommonAdapter<M> extends BaseAdapter<M, CommonViewHolder> {

    public CommonAdapter(Context context, List<M> datas) {
        super(context, datas);
    }

    public CommonAdapter(Context context, List<M> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    public CommonAdapter(Context context, List<M> datas, Pair<Integer, Integer>... layoutIds) {
        super(context, datas, layoutIds);
    }

    @Override
    protected CommonViewHolder getViewHolder(View convertView) {
        return new CommonViewHolder(convertView);
    }
}
