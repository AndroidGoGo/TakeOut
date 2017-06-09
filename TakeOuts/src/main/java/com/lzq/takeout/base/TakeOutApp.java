package com.lzq.takeout.base;

import android.app.Application;

import com.lzq.takeout.model.bean.User;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ${廖昭启} on 2017/6/6.
 */

public class TakeOutApp extends Application {
    public static User  mUser;
    @Override
    public void onCreate() {
        super.onCreate();
        mUser  = new User();
        mUser.setId(-1);
        initSSDK();
       // initJgPush();
    }

    private void initJgPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }

    private void initSSDK() {

    }
}
