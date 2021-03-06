package com.fss.adapter.listview.viewholder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by $USER_NAME on 2019/2/15.
 */
public class CommonViewHolder extends BaseViewHolder {

    private SparseArray<View> mViews = new SparseArray<>();

    public CommonViewHolder(View convertView) {
        super(convertView);
    }


    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViews.append(id, view);
        }
        return (T) view;
    }

    public void setText(int id, String text) {
        TextView textView = getView(id);
        textView.setText(text);
    }

    public void setText(int id, int res) {
        TextView textView = getView(id);
        textView.setText(res);
    }

    public void setOnClickListener(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
    }

    public void setImageResource(int id, int res) {
        ImageView imageView = getView(id);
        imageView.setImageResource(res);
    }

    public void setImageDrawable(int id, Drawable drawable) {
        ImageView imageView = getView(id);
        imageView.setImageDrawable(drawable);
    }

    public void setImageBitmap(int id, Bitmap bitmap) {
        ImageView imageView = getView(id);
        imageView.setImageBitmap(bitmap);
    }
}
