package com.lzq.takeout.presenter;

import com.lzq.takeout.model.bean.ResponseInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ${廖昭启} on 2017/6/5.
 */

public interface TakeOutService {
    @GET("home")
    Call<ResponseInfo> getHomeInfo();

@GET("login")
    Call<ResponseInfo> loginByPhone(@Query("phone") String phoneNumber, @Query("i") int i);
}
