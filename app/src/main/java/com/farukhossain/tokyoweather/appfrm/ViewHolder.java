package com.farukhossain.tokyoweather.appfrm;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.farukhossain.tokyoweather.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView day, status, temp_avg, temp_min;
    public ImageView imageView;

    public ViewHolder(View view) {
        super(view);
        day = (TextView) view.findViewById(R.id.tv_day);
        status = (TextView) view.findViewById(R.id.tv_status);
        temp_avg = (TextView) view.findViewById(R.id.tv_temp_avg);
        temp_min = (TextView) view.findViewById(R.id.tv_temp_min);
        imageView = (ImageView)view.findViewById(R.id.iv_status);
    }
}
