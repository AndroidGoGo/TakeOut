package com.lzq.takeout.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.GoodsInfo;
import com.lzq.takeout.model.bean.GoodsTypeInfo;
import com.lzq.takeout.presenter.GoodsFragmentPresenter;
import com.lzq.takeout.view.activity.BusinessActivity;
import com.lzq.takeout.view.adapter.GoodsAdapter;
import com.lzq.takeout.view.adapter.GoodsTypeRVAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${廖昭启} on 2017/6/8.
 */

public class GoodsFragment extends Fragment {
    @Bind(R.id.rv_goods_type)
    RecyclerView mRvGoodsType;
    @Bind(R.id.slhlv)
    public se.emilsjolander.stickylistheaders.StickyListHeadersListView mSlhlv;
    public GoodsFragmentPresenter mGoodsFragmentPresenter;

    public GoodsTypeRVAdapter mGoodsTypeRVAdapter;
    public GoodsAdapter mGoodsAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_goods, container, false);

        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        mGoodsFragmentPresenter = new GoodsFragmentPresenter(this);
        mRvGoodsType.setAdapter(mGoodsTypeRVAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        long id = ((BusinessActivity) getActivity()).mSeller.getId();
        mGoodsFragmentPresenter.loadBussinessInfo(id);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void onAllGoodsSuccess(List<GoodsInfo> allTypeGoodsList) {
    }

    public void onGoodsTypeSuccess(List<GoodsTypeInfo> goodsTypeInfoList) {
    }
}
