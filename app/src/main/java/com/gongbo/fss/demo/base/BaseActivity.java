package com.gongbo.fss.demo.base;

import android.widget.Toast;

import com.gongbo.fss.base.BaseFssActivity;

public class BaseActivity extends BaseFssActivity {

    //显示Toast信息
    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
