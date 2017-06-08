package com.lzq.takeout.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzq.takeout.model.bean.ResponseInfo;
import com.lzq.takeout.model.bean.Seller;
import com.lzq.takeout.view.fragments.HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

/**
 * Created by ${廖昭启} on 2017/6/5.
 */

public class HomeFragmentPresenter extends NetPresenter {
    HomeFragment mHomeFragment;
    public HomeFragmentPresenter(HomeFragment homeFragment){
       mHomeFragment=homeFragment;

    }
    public void  loadMore(){
        Call<ResponseInfo> homeInfo = mTakeOutService.getHomeInfo();
        homeInfo.enqueue(mCallback);
    }


    @Override
    protected void onFailRespone(String message) {

    }

    @Override
    protected void OnSuccessBug(int code) {

    }

    @Override
    protected void OnSuccess(String data) {
        Gson gson = new Gson();
        //抽取部分数据去解析？
        try {
            JSONObject jsonObject = new JSONObject(data);
            String nearby = jsonObject.getString("nearbySellerList");
            List<Seller> nearbySellerList = gson.fromJson(nearby, new TypeToken<List<Seller>>(){}.getType());

            String other = jsonObject.getString("otherSellerList");
            List<Seller> otherSellerList = gson.fromJson(other, new TypeToken<List<Seller>>(){}.getType());
            //TODO:业务已完成，让view层刷新

            mHomeFragment.onSuccess(nearbySellerList, otherSellerList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
