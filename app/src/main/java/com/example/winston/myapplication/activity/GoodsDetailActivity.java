package com.example.winston.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.winston.myapplication.I;
import com.example.winston.myapplication.R;
import com.example.winston.myapplication.utils.L;

/**
 * Created by Winston on 2016/10/19.
 */

public class GoodsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        int goodsID = getIntent().getIntExtra(I.GoodsDetails.KEY_GOODS_ID,0);
        L.e("details","goodsid="+goodsID);
    }
}
