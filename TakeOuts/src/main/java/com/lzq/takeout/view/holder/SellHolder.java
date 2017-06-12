package com.lzq.takeout.view.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.Seller;
import com.lzq.takeout.view.activity.BusinessActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${廖昭启} on 2017/6/5.
 */

public class SellHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "SellHolder";
    @Bind(R.id.seller_logo)
    ImageView mSellerLogo;
    @Bind(R.id.tvCount)
    TextView mTvCount;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.ratingBar)
    RatingBar mRatingBar;
    @Bind(R.id.tv_home_sale)
    TextView mTvHomeSale;
    @Bind(R.id.tv_home_send_price)
    TextView mTvHomeSendPrice;
    @Bind(R.id.tv_home_distance)
    TextView mTvHomeDistance;
    public   Seller  mSeller;
    private  Context mContext;
    public SellHolder(Context context, View view, Seller seller) {
        super(view);
        mContext = context;
        mSeller=seller;
        ButterKnife.bind(this, view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,BusinessActivity.class);
                intent.putExtra("seller",mSeller);
                Log.d(TAG,"mSeller"+mSeller.getName());
                mContext.startActivity(intent);
            }
        });

    }

    public void setDatas(Seller seller) {
        this.mSeller = seller;
mTvTitle.setText(seller.getName());
        mRatingBar.setRating(Float.parseFloat(seller.getScore()));
        mTvHomeSale.setText("月售" + seller.getSale() + "单");
        mTvHomeSendPrice.setText("￥" + seller.getSendPrice() + "起送/配送费￥" + seller.getDeliveryFee());
        mTvHomeDistance.setText(seller.getDistance());
    }
}
