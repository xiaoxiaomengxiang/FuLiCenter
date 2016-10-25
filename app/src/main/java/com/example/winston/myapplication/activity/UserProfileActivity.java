package com.example.winston.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.winston.myapplication.FuLiCenterApplication;
import com.example.winston.myapplication.R;
import com.example.winston.myapplication.bean.User;
import com.example.winston.myapplication.dao.SharePrefrenceUtils;
import com.example.winston.myapplication.utils.ImageLoader;
import com.example.winston.myapplication.utils.MFGT;
import com.example.winston.myapplication.view.DisplayUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfileActivity extends BaseActivity {

    @BindView(R.id.tv_user_name_avatar)
    TextView mtvUserNameAvatar;
    @BindView(R.id.iv_user_profile_avatar)
    ImageView mivUserProfileAvatar;
    @BindView(R.id.tv_user_name)
    TextView mtvUserName;
    @BindView(R.id.tv_user_username)
    TextView mtvUserUsername;

    UserProfileActivity mContext;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        mContext=this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        DisplayUtils.initBackWithTitle(mContext,getResources().getString(R.string.user_profile));
    }

    @Override
    protected void initData() {
        user = FuLiCenterApplication.getUser();
        if (user != null) {
            ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user), mContext, mivUserProfileAvatar);
            mtvUserUsername.setText(user.getMuserName());
            mtvUserName.setText(user.getMuserNick());
        } else {
            finish();
        }
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btn_Logout, R.id.layout_user_profile_avatar, R.id.layout_user_profile_username, R.id.layout_user_profile_usernam})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Logout:
                break;
            case R.id.layout_user_profile_avatar:
                break;
            case R.id.layout_user_profile_username:
                break;
            case R.id.layout_user_profile_usernam:
                logout();
                break;
        }
    }

    private void logout() {
        if (user != null) {
            SharePrefrenceUtils.getInstence(mContext).removeUser();
            FuLiCenterApplication.setUser(null);
            MFGT.gotoLogin(mContext);
        }
        finish();
    }
}
