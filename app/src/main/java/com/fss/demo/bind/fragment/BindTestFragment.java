package com.fss.demo.bind.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.fss.bind.annotation.BindFragment;
import com.fss.demo.R;
import com.fss.demo.base.BaseFragment;

import static com.fss.demo.util.ToastUtils.showToast;

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
