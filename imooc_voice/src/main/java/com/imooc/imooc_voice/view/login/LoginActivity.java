package com.imooc.imooc_voice.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;

import androidx.annotation.Nullable;

import com.imooc.imooc_voice.R;
import com.imooc.imooc_voice.api.RequestCenter;
import com.imooc.imooc_voice.model.user.User;
import com.imooc.imooc_voice.view.login.manager.UserManager;
import com.imooc.imooc_voice.view.login.user.LoginEvent;
import com.imooc.lib_common_ui.base.BaseActivity;
import com.imooc.lib_network.okhttp.listener.DisposeDataListener;

import org.greenrobot.eventbus.EventBus;


public class LoginActivity extends BaseActivity implements DisposeDataListener {

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        //初始化P层
        findViewById(R.id.login_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestCenter.login(LoginActivity.this);
            }
        });
    }

    @Override
    public void onSuccess(Object responseObj) {
        User user = (User) responseObj;
        UserManager.getInstance().saveUser(user);
        EventBus.getDefault().post(new LoginEvent());
        finish();
    }

    @Override
    public void onFailure(Object responnseObj) {
        //登陆失败逻辑
    }
}
