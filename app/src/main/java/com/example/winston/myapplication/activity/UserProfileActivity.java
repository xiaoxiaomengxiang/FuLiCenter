package com.example.winston.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.winston.myapplication.FuLiCenterApplication;
import com.example.winston.myapplication.I;
import com.example.winston.myapplication.R;
import com.example.winston.myapplication.bean.Result;
import com.example.winston.myapplication.bean.User;
import com.example.winston.myapplication.dao.SharePrefrenceUtils;
import com.example.winston.myapplication.net.NetDao;
import com.example.winston.myapplication.net.OkHttpUtils;
import com.example.winston.myapplication.utils.CommonUtils;
import com.example.winston.myapplication.utils.ImageLoader;
import com.example.winston.myapplication.utils.L;
import com.example.winston.myapplication.utils.MFGT;
import com.example.winston.myapplication.utils.OnSetAvatarListener;
import com.example.winston.myapplication.utils.ResultUtils;
import com.example.winston.myapplication.view.DisplayUtils;

import java.io.File;

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
    OnSetAvatarListener mOnSetAvatarListener;
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
                logout();
                break;
            case R.id.layout_user_profile_avatar:
                mOnSetAvatarListener = new OnSetAvatarListener(mContext,R.id.layout_upload_avatar,
                                                user.getMuserName(),I.AVATAR_TYPE_USER_PATH);
                break;
            case R.id.layout_user_profile_username:
                CommonUtils.showLongToast(R.string.username_connot_be_modify);
                break;
            case R.id.layout_user_profile_usernam:
                MFGT.gotoUpdateNick(mContext);
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
    @Override
        protected void onResume() {
                super.onResume();
                showInfo();
            }

                @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                    if(resultCode==RESULT_OK&&requestCode== I.REQUEST_CODE_NICK) {
                        L.e("onActivityResult,requestCode=" + requestCode + ",resultCode=" + resultCode);
                        if (resultCode != RESULT_OK) {
                            return;
                        }
                        mOnSetAvatarListener.setAvatar(requestCode, data, mivUserProfileAvatar);
                        if (requestCode == I.REQUEST_CODE_NICK) {
                            CommonUtils.showLongToast(R.string.update_user_nick_success);
                        }
                        if (requestCode == OnSetAvatarListener.REQUEST_CROP_PHOTO) {
                            updateAvatar();
                        }
                    }
                }

    private void updateAvatar() {
                //file=/storage/emulated/0/Android/data/cn.ucai.fulicenter/files/Pictures/a952700
                        //file=/storage/emulated/0/Android/data/cn.ucai.fulicenter/files/Pictures/user_avatar/a952700.jpg
                                File file = new File(OnSetAvatarListener.getAvatarPath(mContext,
                                user.getMavatarPath()+"/"+user.getMuserName()
                                        +I.AVATAR_SUFFIX_JPG));
                L.e("file="+file.exists());
                L.e("file="+file.getAbsolutePath());
                final ProgressDialog pd = new ProgressDialog(mContext);
                pd.setMessage(getResources().getString(R.string.update_user_avatar));
                pd.show();
                NetDao.updateAvatar(mContext, user.getMuserName(), file, new OkHttpUtils.OnCompleteListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                                L.e("s="+s);
                                Result result = ResultUtils.getResultFromJson(s,User.class);
                                L.e("result="+result);
                                if(result==null){
                                        CommonUtils.showLongToast(R.string.update_user_avatar_fail);
                                    }else{
                                        User u = (User) result.getRetData();
                                        if(result.isRetMsg()){
                                                FuLiCenterApplication.setUser(u);
                                                ImageLoader.setAvatar(ImageLoader.getAvatarUrl(u),mContext,mivUserProfileAvatar);
                                                CommonUtils.showLongToast(R.string.update_user_avatar_success);
                                            }else{
                                                CommonUtils.showLongToast(R.string.update_user_avatar_fail);
                                            }
                                    }
                                pd.dismiss();
                            }

                                @Override
                        public void onError(String error) {
                                pd.dismiss();
                                CommonUtils.showLongToast(R.string.update_user_avatar_fail);
                                L.e("error="+error);
                            }
                    });
    }

    private void showInfo(){
            user = FuLiCenterApplication.getUser();
               if(user!=null){
                        ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user),mContext,mivUserProfileAvatar);
                   mtvUserUsername.setText(user.getMuserName());
                   mtvUserName.setText(user.getMuserNick());
                    }
            }
}
