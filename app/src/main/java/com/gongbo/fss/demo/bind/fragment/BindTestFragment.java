package com.gongbo.fss.demo.bind.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gongbo.fss.bind.annotation.BindFragment;
import com.gongbo.fss.demo.R;
import com.gongbo.fss.demo.base.BaseFragment;

import static com.gongbo.fss.demo.util.ToastUtils.showToast;

@BindFragment(R.layout.fragment_bind_test)
public class BindTestFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected void initView() {
        showToast("hello");
    }
}
