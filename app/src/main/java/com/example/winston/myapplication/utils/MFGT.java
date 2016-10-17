package com.example.winston.myapplication.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.winston.myapplication.R;
import com.example.winston.myapplication.activity.MainActivity;

/**
 * Created by Winston on 2016/10/14.
 * 辅助跳转
 */
    public class MFGT {
        public static void finish(Activity activity){
            activity.finish();
            activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
        }
        public static void gotoMainActivity(Activity context){
            startActivity(context, MainActivity.class);
        }
        public static void startActivity(Activity context,Class<?> cls){
            Intent intent = new Intent();
            intent.setClass(context,cls);
            context.startActivity(intent);
            context.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        }
    }

