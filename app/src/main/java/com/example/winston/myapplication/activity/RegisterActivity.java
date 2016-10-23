package com.example.winston.myapplication.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.winston.myapplication.R;
import com.example.winston.myapplication.view.DisplayUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.nick)
    EditText mNick;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.confirm_password)
    EditText mConfirmPassword;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        DisplayUtils.initBackWithTitle(this,"账户注册");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}