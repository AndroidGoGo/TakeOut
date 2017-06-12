package com.lzq.takeout.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.GoodsInfo;
import com.lzq.takeout.view.fragments.GoodsFragment;
import com.lzq.takeout.view.holder.GoodsViewHolder;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by ${廖昭启} on 2017/6/8.
 */

public class GoodsAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private static final String TAG = "GoodsAdapter";
    private final GoodsFragment mGoodsFragmet;
    private List<GoodsInfo> mGoodsInfos = new ArrayList<>();

    public void getGoodsInfos(List<GoodsInfo> infoList) {
        Log.d(TAG, "GoodsAdapter获取到数据");
        mGoodsInfos = infoList;
        notifyDataSetChanged();

    }




    public GoodsAdapter(GoodsFragment goodsFragment) {
        mGoodsFragmet = goodsFragment;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        if (mGoodsInfos == null) {
           Log.d("吸顶头条目","空指针啦~~~~");
            return null;
        }
        //  Log.d(TAG,mGoodsInfos.get(position).getTypeName());
        View view = LayoutInflater.from(mGoodsFragmet.getActivity()).inflate(R.layout.item_type_header, parent, false);
        ((TextView) view).setText(mGoodsInfos.get(position).getTypeName());
        return view;
    }

    @Override
    public long getHeaderId(int position) {
        return mGoodsInfos.get(position).getTypeId();
    }

    @Override
    public int getCount() {
        return mGoodsInfos == null ? 0 : mGoodsInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mGoodsInfos == null ? null : mGoodsInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GoodsViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mGoodsFragmet.getActivity(), R.layout.item_goods, null);
            viewHolder = new GoodsViewHolder(mGoodsFragmet, mGoodsInfos, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GoodsViewHolder) convertView.getTag();
        }
        Log.d("GoodsAdapter:  商品详情", mGoodsInfos.get(position).getName() + 1);
        viewHolder.setDatas(mGoodsInfos.get(position));


        return convertView;
    }
}
