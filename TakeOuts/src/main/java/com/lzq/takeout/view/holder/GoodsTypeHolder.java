package com.lzq.takeout.view.holder;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.GoodsTypeInfo;
import com.lzq.takeout.view.adapter.GoodsTypeRVAdapter;
import com.lzq.takeout.view.fragments.GoodsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${廖昭启} on 2017/6/9.
 */

public class GoodsTypeHolder extends RecyclerView.ViewHolder {
    private int mSelectPosition;
    @Bind(R.id.tvCount)
    TextView mTvCount;
    @Bind(R.id.type)
    TextView mType;
    private GoodsTypeInfo mGoodsTypeInfo;
    private int mPosition;
    private View mView;

    private GoodsFragment mGoodsFragment;
    private GoodsTypeRVAdapter mGoodsTypeRVAdapter;

    public GoodsTypeHolder(GoodsFragment goodsFragment, GoodsTypeRVAdapter goodsTypeRVAdapter,  View itemView) {

        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mGoodsFragment = goodsFragment;
        mGoodsTypeRVAdapter = goodsTypeRVAdapter;

        this.mView = itemView;

        initClickListener(itemView);
    }

    private void initClickListener(View itemView) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPosition = mPosition;
                mGoodsTypeRVAdapter.notifyDataSetChanged();
                int id = mGoodsTypeInfo.getId();
                int positionById = mGoodsFragment.mGoodsFragmentPresenter.getPositionById(id);
                mGoodsFragment.mSlhlv.setSelection(positionById);
            }
        });
    }

    public void setDatas(GoodsTypeInfo goodsTypeInfo, int position) {
        mGoodsTypeInfo = goodsTypeInfo;
        mPosition = position;
        if (mPosition == mSelectPosition) {
            mView.setBackgroundColor(Color.WHITE);
            mType.setTextColor(Color.BLACK);
            mType.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            mView.setBackgroundColor(Color.parseColor("#b9dedcdc"));
            mType.setTextColor(Color.GRAY);
            mType.setTypeface(Typeface.DEFAULT);
        }
        mType.setText(goodsTypeInfo.getName());
        if (goodsTypeInfo.getRedCount() > 0) {
            mTvCount.setVisibility(View.VISIBLE);
        } else {
            mTvCount.setVisibility(View.GONE);
        }
        mTvCount.setText(mGoodsTypeInfo.getRedCount() + "");
    }

}
