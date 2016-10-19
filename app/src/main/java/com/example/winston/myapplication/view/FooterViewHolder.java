package com.example.winston.myapplication.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.winston.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Winston on 2016/10/19.
 */

public class FooterViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvFooter)
    public TextView mTvFooter;

    public FooterViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
