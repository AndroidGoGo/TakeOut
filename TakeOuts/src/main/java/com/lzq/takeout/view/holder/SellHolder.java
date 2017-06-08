package com.lzq.takeout.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.Seller;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${廖昭启} on 2017/6/5.
 */

public class SellHolder extends RecyclerView.ViewHolder {
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
    public SellHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void setDatas(Seller seller) {
mTvTitle.setText(seller.getName());
    }
}
