package com.lzq.takeout.view.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzq.takeout.R;
import com.lzq.takeout.base.TakeOutApp;
import com.lzq.takeout.view.activity.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${廖昭启} on 2017/6/5.
 */

public class UserFragment extends Fragment {
    private static final String TAG ="UserFragment" ;
    @Bind(R.id.tv_user_setting)
    ImageView mTvUserSetting;
    @Bind(R.id.iv_user_notice)
    ImageView mIvUserNotice;
    @Bind(R.id.login)
    ImageView mLogin;
    @Bind(R.id.username)
    TextView mUsername;
    @Bind(R.id.phone)
    TextView mPhone;
    @Bind(R.id.ll_userinfo)
    LinearLayout mLlUserinfo;
    @Bind(R.id.iv_address_manager)
    ImageView mIvAddressManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_user, null);

        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        int id = TakeOutApp.mUser.getId();
        Log.d(TAG,id+"");
        if(id<0){
            mLogin.setVisibility(View.VISIBLE);
            mLlUserinfo.setVisibility(View.GONE);
        }else {
           mLlUserinfo.setVisibility(View.VISIBLE);
            mLogin.setVisibility(View.GONE);
            mUsername.setText("欢迎您,尊敬的"+TakeOutApp.mUser.getName());
            mPhone.setText(TakeOutApp.mUser.getPhone());
        }
    }

    @OnClick({R.id.tv_user_setting, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_user_setting:
                break;
            case R.id.login:
                Intent inten = new Intent(getActivity(), LoginActivity.class);

                startActivity(inten);
                break;
        }
    }
}
