package com.lzq.takeout.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzq.takeout.R;
import com.lzq.takeout.presenter.LoginActivityPresenter;
import com.lzq.takeout.util.SMSUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by ${廖昭启} on 2017/6/6.
 */

public class LoginActivity extends Activity {
    private Context mContext;
    private static final int TIME_CUTING = 100;
    private static final int TIME_OUT = 101;
    private  String mPhoneNumber;
    private  String mConfiCode;
    private  LoginActivityPresenter mPresenter;
    @Bind(R.id.iv_user_back)
    ImageView mIvUserBack;
    @Bind(R.id.iv_user_password_login)
    TextView mIvUserPasswordLogin;
    @Bind(R.id.et_user_phone)
    EditText mEtUserPhone;
    @Bind(R.id.tv_user_code)
    TextView mTvUserCode;
    @Bind(R.id.et_user_code)
    EditText mEtUserCode;
    @Bind(R.id.login)
    TextView mLogin;
    private EventHandler mEventHandler = new EventHandler(){
        @Override
        public void afterEvent(int i, int i1, Object o) {
            if(i1==SMSSDK.RESULT_COMPLETE){
                if(i==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                    mPresenter.loginByPhone(mPhoneNumber,2);
                }else if(i==SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE){

                }else if(i== SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){}}else {
                ((Throwable)o).printStackTrace();
            }
            }

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = new LoginActivityPresenter(this);
        SMSSDK.initSDK(this, "181c39656a7ae", "29eea8ebe615953942de64e153c4df34");
        SMSSDK.registerEventHandler(mEventHandler);
        mContext =this;
        ButterKnife.bind(this);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case TIME_CUTING:

                    mTvUserCode.setText("(" + time + ")秒后重新发送");
                    break;
                case TIME_OUT:
                    mTvUserCode.setText("点击重新发送");
                    mTvUserCode.setEnabled(true);
                    time=10;
                    break;
            }
            super.handleMessage(msg);
        }
    };
    int time = 10;

    @OnClick({R.id.tv_user_code, R.id.login})
    public void onViewClicked(View view) {
        mPhoneNumber = mEtUserPhone.getText().toString().trim();
        if (!SMSUtil.judgePhoneNums(this,mPhoneNumber)){
            return;
        }
        switch (view.getId()) {

            case R.id.tv_user_code:

                if (TextUtils.isEmpty(mPhoneNumber)){
                    Toast.makeText(mContext,"号码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    SMSSDK.getVerificationCode("86", mPhoneNumber);

                mTvUserCode.setEnabled(false);
                new Thread(new TimeTask()).start();}


                break;
            case R.id.login:
               // submitVerificationCode(String country, String phone, String code)
                mConfiCode = mEtUserCode.getText().toString().trim();
                if(mConfiCode.isEmpty()){
                    Toast.makeText(mContext,"验证码不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    SMSSDK.submitVerificationCode("86", mPhoneNumber, mConfiCode);//提交
                    mPresenter.loginByPhone(mPhoneNumber,2);

                }


                break;
        }
    }

    public void onFailLogin() {
        Toast.makeText(mContext,"验证码错误",Toast.LENGTH_SHORT).show();
    }

    public void onSuccessBugLogin() {
        Toast.makeText(mContext,"服务器繁忙,请您稍后再试....",Toast.LENGTH_SHORT).show();
    }

    public void onSuccessLogin() {

        finish();
    }

    class TimeTask implements Runnable {
        @Override
        public void run() {
            for (; time > 0; time--) {
                SystemClock.sleep(1000);
                mHandler.sendEmptyMessage(TIME_CUTING);
            }
            mHandler.sendEmptyMessage(TIME_OUT);

        }
    }
}
