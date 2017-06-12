package com.lzq.takeout.view.holder;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.GoodsInfo;
import com.lzq.takeout.util.PriceFormater;
import com.lzq.takeout.view.fragments.GoodsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${廖昭启} on 2017/6/11.
 */

public class GoodsViewHolder {
    private static final long DURATION = 1500;
    @Bind(R.id.iv_icon)
    ImageView mIvIcon;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_zucheng)
    TextView mTvZucheng;
    @Bind(R.id.tv_yueshaoshounum)
    TextView mTvYueshaoshounum;
    @Bind(R.id.tv_newprice)
    TextView mTvNewprice;
    @Bind(R.id.tv_oldprice)
    TextView mTvOldprice;
    @Bind(R.id.ib_minus)
    ImageButton mIbMinus;
    @Bind(R.id.tv_count)
    TextView mTvCount;
    @Bind(R.id.ib_add)
    ImageButton mIbAdd;
    private List<GoodsInfo> mGoodsInfos;
    private GoodsFragment mGoodsFragment;
    private Context mContext;
    private GoodsInfo mGoodsInfo;

    public GoodsViewHolder(GoodsFragment goodsFragmet, List<GoodsInfo> goodsInfos, View convertView) {

        ButterKnife.bind(this, convertView);
        this.mGoodsFragment = goodsFragmet;

        this.mGoodsInfos = goodsInfos;
        mContext = mGoodsFragment.getActivity();


    }


    public void setDatas(GoodsInfo goodsInfos) {
        mGoodsInfo = goodsInfos;
       // Log.d("GoodsAdapter: 商品信息", goodsInfos.getName() + 1);
        mTvName.setText(goodsInfos.getName());


        //TODO:
        //Log.e("goods", Constant.HOST + goodsInfo.getIcon());
        Picasso.with(mContext).load(goodsInfos.getIcon()).into(mIvIcon);
        mTvZucheng.setText(goodsInfos.getForm());

        mTvYueshaoshounum.setText("月售" + goodsInfos.getMonthSaleNum() + "份");
        mTvNewprice.setText(PriceFormater.format(Float.parseFloat(goodsInfos.getNewPrice())));

        if (goodsInfos.getOldPrice() > 0) {
            mTvOldprice.setVisibility(View.VISIBLE);
            mTvOldprice.setText(PriceFormater.format(goodsInfos.getOldPrice()));

            mTvOldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            mTvOldprice.setVisibility(View.GONE);
        }

        //count>0才显示
        int count = goodsInfos.getCount();
        if (count > 0) {
            mTvCount.setVisibility(View.VISIBLE);
            mIbMinus.setVisibility(View.VISIBLE);
        } else {
            mTvCount.setVisibility(View.INVISIBLE);
            mIbMinus.setVisibility(View.INVISIBLE);
        }
        mTvCount.setText(count + "");
    }
}
