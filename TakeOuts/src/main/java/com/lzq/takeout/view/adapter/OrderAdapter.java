package com.lzq.takeout.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.Order;
import com.lzq.takeout.model.bean.Seller;
import com.lzq.takeout.util.OrderObservable;
import com.lzq.takeout.view.holder.OderFragmentViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by ${廖昭启} on 2017/6/8.
 */

public class OrderAdapter extends RecyclerView.Adapter implements Observer {
    Context mContext;
    private Seller mSeller;

    public OrderAdapter(Context context) {
        mContext = context;

       OrderObservable.getInstance().addObserver(this);
    }

    private List<Order> mOrderList = new ArrayList<>();

    public void setOrderList(List<Order> orderList) {
        mOrderList = orderList;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_order_item, parent, false);
        OderFragmentViewHolder viewHolder = new OderFragmentViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public  void setDatas(){

    }
    public String getOrderTypeInfo(String type) {
        /**
         * 订单状态
         * 1 未支付 2 已提交订单 3 商家接单  4 配送中,等待送达 5已送达 6 取消的订单
         */
//            public static final String ORDERTYPE_UNPAYMENT = "10";
//            public static final String ORDERTYPE_SUBMIT = "20";
//            public static final String ORDERTYPE_RECEIVEORDER = "30";
//            public static final String ORDERTYPE_DISTRIBUTION = "40";
//            public static final String ORDERTYPE_SERVED = "50";
//            public static final String ORDERTYPE_CANCELLEDORDER = "60";

        String typeInfo = "";
        switch (type) {
            case OrderObservable.ORDERTYPE_UNPAYMENT:
                typeInfo = "未支付";
                break;
            case OrderObservable.ORDERTYPE_SUBMIT:
                typeInfo = "已提交订单";
                break;
            case OrderObservable.ORDERTYPE_RECEIVEORDER:
                typeInfo = "商家接单";
                break;
            case OrderObservable.ORDERTYPE_DISTRIBUTION:
                typeInfo = "配送中";
                break;
            case OrderObservable.ORDERTYPE_SERVED:
                typeInfo = "已送达";
                break;
            case OrderObservable.ORDERTYPE_CANCELLEDORDER:
                typeInfo = "取消的订单";
                break;
        }
        return typeInfo;
    }

    @Override
    public void update(Observable o, Object arg) {
        Map<String, String> data = (Map<String, String>) arg;
        String pushOrderId = data.get("orderId");
        String pushType = data.get("type");

        int index = -1;
        for (int i = 0; i < mOrderList.size(); i++) {
            Order order = mOrderList.get(i);
            if (order.id.equals(pushOrderId)) {
                //改变他的状态
                order.type = pushType;
                index = i;
            }
        }
        if (index != -1) {
            //找到了
            notifyItemChanged(index);
        }
    }
}
