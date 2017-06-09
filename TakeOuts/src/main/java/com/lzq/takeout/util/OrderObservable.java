package com.lzq.takeout.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by ${廖昭启} on 2017/6/8.
 */

public class OrderObservable extends Observable {
    public  static  OrderObservable sOrderObservable = new OrderObservable();

    private OrderObservable() {

    }
    public  static OrderObservable  getInstance(){
        return  sOrderObservable;
    }

    /* 订单状态
      * 1 未支付 2 已提交订单 3 商家接单  4 配送中,等待送达 5已送达 6 取消的订单*/
    public static final String ORDERTYPE_UNPAYMENT = "10";
    public static final String ORDERTYPE_SUBMIT = "20";
    public static final String ORDERTYPE_RECEIVEORDER = "30";
    public static final String ORDERTYPE_DISTRIBUTION = "40";
    // 骑手状态：接单、取餐、送餐
    public static final String ORDERTYPE_DISTRIBUTION_RIDER_RECEIVE = "43";
    public static final String ORDERTYPE_DISTRIBUTION_RIDER_TAKE_MEAL = "46";
    public static final String ORDERTYPE_DISTRIBUTION_RIDER_GIVE_MEAL = "48";

    public static final String ORDERTYPE_SERVED = "50";
    public static final String ORDERTYPE_CANCELLEDORDER = "60";

public  void newMsgComing(String extra){
    Map<String,String>  msg = proceesJson(extra);

}

    private Map<String,String> proceesJson(String extra) {
        Map<String, String> hashMap = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(extra);
            String orderId = jsonObject.getString("orderId");
            String type = jsonObject.getString("type");
            hashMap.put("orderId",orderId);
            hashMap.put("type",type+"");
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
