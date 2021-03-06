package com.lzq.takeout.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.lzq.takeout.base.TakeOutApp;
import com.lzq.takeout.model.bean.ResponseInfo;
import com.lzq.takeout.model.bean.User;
import com.lzq.takeout.model.dao.TakeOutOpenHelpe;
import com.lzq.takeout.view.activity.LoginActivity;

import java.sql.SQLException;
import java.sql.Savepoint;

import retrofit2.Call;

/**
 * Created by ${廖昭启} on 2017/6/7.
 */

public class LoginActivityPresenter extends  NetPresenter{
    private static final String TAG = "LoginActivityPresenter";
    private LoginActivity  mLoginActivity;

    public LoginActivityPresenter(LoginActivity loginActivity) {
        mLoginActivity = loginActivity;
    }

    public  void loginByPhone(String phoneNumber, int i){
        Call<ResponseInfo> mCall = mTakeOutService.loginByPhone(phoneNumber,i);
        mCall.enqueue(mCallback);
    }

    @Override
    protected void onFailRespone(String message) {
        Toast.makeText(mLoginActivity,"请求失败",Toast.LENGTH_SHORT).show();
   mLoginActivity.onFailLogin();
    }

    @Override
    protected void OnSuccessBug(int code) {
        Toast.makeText(mLoginActivity,"请求失败",Toast.LENGTH_SHORT).show();
mLoginActivity.onSuccessBugLogin();
    }

    @Override
    protected void OnSuccess(String data) {
        Log.d(TAG,"q请求成功");
        Toast.makeText(mLoginActivity,"请求成功",Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        User user = gson.fromJson(data, User.class);
        int id = user.getId();
        Log.d(TAG,id+"");
        TakeOutApp.mUser = user;
        TakeOutOpenHelpe helpe = new TakeOutOpenHelpe(mLoginActivity);
        AndroidDatabaseConnection connection = new AndroidDatabaseConnection(helpe.getWritableDatabase(), true);
        Savepoint savepoint  = null;
        try {
            savepoint = connection.setSavePoint("start");
            connection.setAutoCommit(false);
            Dao<User, Integer> dao = helpe.getDao(User.class);
            User userId = dao.queryForId(36);
            if(userId==null){
                dao.update(user);
            }else {
                dao.create(user);
            }
            connection.commit(savepoint);
        } catch (SQLException e) {

            e.printStackTrace();
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
               mLoginActivity.onFailLogin();
            }
        }

        mLoginActivity.onSuccessLogin();
    }
}
