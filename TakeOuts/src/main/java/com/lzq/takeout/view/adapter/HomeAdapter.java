package com.lzq.takeout.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.Seller;
import com.lzq.takeout.view.holder.SellHolder;
import com.lzq.takeout.view.holder.TitleHolder;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ${廖昭启} on 2017/6/5.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    Context mContext;

    public HomeAdapter(Context context) {
        mContext = context;
    }

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_SELL = 1;

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_TITLE : TYPE_SELL;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:

                View view = View.inflate(mContext, R.layout.item_title, null);
                TitleHolder titleHolder = new TitleHolder(view);

                Log.d(TAG, "头布局");
                return titleHolder;
            case TYPE_SELL:
                View view2 = View.inflate(mContext, R.layout.item_seller, null);
                SellHolder sellHolder = new SellHolder(mContext,view2);
                return sellHolder;
            default:
                return null;


        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_TITLE:
                TitleHolder titleHolder = (TitleHolder) holder;
                titleHolder.setDatas(".......");
                Log.d(TAG, "头布局");
                break;
            case TYPE_SELL:
                SellHolder sellHolder = (SellHolder) holder;
                sellHolder.setDatas(mSellerDatas.get(position));
                break;

        }

    }

    List<Seller> mSellerDatas = new ArrayList<>();

    @Override
    public int getItemCount() {
        return mSellerDatas == null ? 0 : mSellerDatas.size();
    }

    public void setDatas(List<Seller> nearSeller, List<Seller> otherSeller) {
        mSellerDatas.clear();
        mSellerDatas.addAll(nearSeller);
        mSellerDatas.addAll(otherSeller);
        notifyDataSetChanged();

    }


}
