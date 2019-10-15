package com.gongbo.fss.adapter.recyclerview.viewholder;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by $USER_NAME on 2018/9/10.
 */
public class DataBindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T binding;

    public DataBindingViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public T getBinding() {
        return binding;
    }
}
