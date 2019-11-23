package com.fss.router.api.callback;

import android.content.Intent;


public interface OnActivityResult {
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
