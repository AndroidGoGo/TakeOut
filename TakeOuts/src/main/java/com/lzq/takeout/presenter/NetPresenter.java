package com.lzq.takeout.presenter;

import android.util.Log;
import android.widget.Toast;

import com.lzq.takeout.model.bean.ResponseInfo;
import com.lzq.takeout.util.Contant;
import com.lzq.takeout.view.fragments.OrderFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${廖昭启} on 2017/6/5.
 */

public abstract class NetPresenter {
    private static final String TAG ="NetPresenter" ;
    protected Retrofit  mRetrofit;
    protected TakeOutService mTakeOutService;
    private OrderFragment mOrderFragment;

    public NetPresenter() {
        mRetrofit = new Retrofit.Builder().baseUrl(Contant.HOST).addConverterFactory(GsonConverterFactory.create()).build();
        mTakeOutService = mRetrofit.create(TakeOutService.class);
    }
    protected Callback mCallback = new Callback<ResponseInfo>() {
        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
            if (response.isSuccessful()){
                ResponseInfo body = response.body();
                if(body==null){
                    Toast.makeText(mOrderFragment.getActivity(),"观察者是空的",Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"请求空数据");
                    return;
                }
                OnSuccess(body.getData());
            }else {
                OnSuccessBug(response.code());
            }

        }



        @Override
        public void onFailure(Call call, Throwable t) {
onFailRespone(t.getMessage());
        }
    };

    protected abstract void onFailRespone(String message);

    protected abstract void OnSuccessBug(int code);

    protected abstract void OnSuccess(String data);


}
