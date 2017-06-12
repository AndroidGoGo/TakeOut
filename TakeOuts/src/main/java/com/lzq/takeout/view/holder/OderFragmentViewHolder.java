package com.lzq.takeout.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.Order;
import com.lzq.takeout.view.adapter.OrderAdapter;

import butterknife.Bind;

/**
 * Created by ${廖昭启} on 2017/6/10.
 */

public class OderFragmentViewHolder extends RecyclerView.ViewHolder {
    private OrderAdapter mAdapter;
    @Bind(R.id.iv_order_item_seller_logo)
    ImageView mIvOrderItemSellerLogo;
    @Bind(R.id.tv_order_item_seller_name)
    TextView mTvOrderItemSellerName;
    @Bind(R.id.tv_order_item_type)
    TextView mTvOrderItemType;
    @Bind(R.id.tv_order_item_time)
    TextView mTvOrderItemTime;
    @Bind(R.id.tv_order_item_foods)
    TextView mTvOrderItemFoods;
    @Bind(R.id.tv_order_item_money)
    TextView mTvOrderItemMoney;
    @Bind(R.id.tv_order_item_multi_function)
    TextView mTvOrderItemMultiFunction;
    public OderFragmentViewHolder(View itemView) {
        super(itemView);
    }

    public  void  setDatas(Order order){
        mTvOrderItemSellerName.setText(order.seller.getName());
        mTvOrderItemType.setText(mAdapter.getOrderTypeInfo(order.type)); //订单的状态
    }


}
