package com.example.winston.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.winston.myapplication.FuLiCenterApplication;
import com.example.winston.myapplication.R;
import com.example.winston.myapplication.bean.User;
import com.example.winston.myapplication.dao.SharePrefrenceUtils;
import com.example.winston.myapplication.dao.UserDao;
import com.example.winston.myapplication.utils.L;
import com.example.winston.myapplication.utils.MFGT;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity .class.getSimpleName();

    private final long sleepTime = 2000;
    SplashActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = FuLiCenterApplication.getUser();
                L.e(TAG,"fulicenter,user="+user);
                String username = SharePrefrenceUtils.getInstence(mContext).getUser();
                L.e(TAG,"fulicenter,username="+username);
                if(user==null && username!=null) {
                    UserDao dao = new UserDao(mContext);
                    user = dao.getUser(username);
                    L.e(TAG,"database,user="+user);
                    if(user!=null){
                        FuLiCenterApplication.setUser(user);
                    }
                }
                MFGT.gotoMainActivity(SplashActivity.this);
                finish();
            }
        },sleepTime);
    }
}