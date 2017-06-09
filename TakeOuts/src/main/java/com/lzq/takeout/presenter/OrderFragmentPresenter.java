package com.lzq.takeout.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzq.takeout.model.bean.Order;
import com.lzq.takeout.model.bean.ResponseInfo;
import com.lzq.takeout.view.fragments.OrderFragment;

import java.util.List;

import retrofit2.Call;

/**
 * Created by ${廖昭启} on 2017/6/8.
 */

public class OrderFragmentPresenter extends  NetPresenter {
    private OrderFragment mOrderFragment;

    public OrderFragmentPresenter(OrderFragment orderFragment) {
        mOrderFragment = orderFragment;
    }
protected  void  getOrderList(String id){
    Call<ResponseInfo>  mCall = mTakeOutService.getOrderList(id);
    mCall.enqueue(mCallback);
}
    @Override
    protected void onFailRespone(String message) {

    }

    @Override
    protected void OnSuccessBug(int code) {

    }

    @Override
    protected void OnSuccess(String data) {
        Gson  mGson = new Gson();
        List<Order>  orderList  = mGson.fromJson(data,new TypeToken<List<Order>>(){}.getType());
    }
}
