package com.lzq.takeout.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.GoodsInfo;
import com.lzq.takeout.model.bean.GoodsTypeInfo;
import com.lzq.takeout.view.fragments.GoodsFragment;
import com.lzq.takeout.view.holder.GoodsTypeHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${廖昭启} on 2017/6/8.
 */

public class GoodsTypeRVAdapter extends RecyclerView.Adapter {
    public int selectIndex;
    private GoodsFragment mGoodsFragment;
    // private List<GoodsInfo> mGoodsInfo = new ArrayList<>();
    private Context mContext;
    private List<GoodsInfo> mGoodsInfo;

    public GoodsTypeRVAdapter(GoodsFragment goodsFragment) {
        this.mGoodsFragment = goodsFragment;
        mContext = goodsFragment.getActivity();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_type, parent, false);
        GoodsTypeHolder goodsTypeHolder = new GoodsTypeHolder(mGoodsFragment, this, view);

        return goodsTypeHolder;
    }

    private List<GoodsTypeInfo> mGoodsTypeInfoList = new ArrayList<>();

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoodsTypeHolder goodsTypeHolder = (GoodsTypeHolder) holder;
        goodsTypeHolder.setDatas(mGoodsTypeInfoList.get(position), position);


       // Log.d("商品列表的ViewHolder", mGoodsTypeInfoList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mGoodsTypeInfoList == null ? 0 : mGoodsTypeInfoList.size();
    }




    public void setDatas(List<GoodsInfo> goodsTypeInfoList) {
        mGoodsInfo = goodsTypeInfoList;
        notifyDataSetChanged();
    }



    public void setGoodsTypeInfoList(List<GoodsTypeInfo> allTypeGoodsList) {
        mGoodsTypeInfoList = allTypeGoodsList;

        notifyDataSetChanged();
    }
}
