package com.lzq.takeout.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lzq.takeout.R;
import com.lzq.takeout.base.TakeOutApp;
import com.lzq.takeout.view.adapter.OrderAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${廖昭启} on 2017/6/5.
 */

public class OrderFragment extends Fragment {
    @Bind(R.id.rv_order_list)
    RecyclerView mRvOrderList;
    @Bind(R.id.srl_order)
    SwipeRefreshLayout mSrlOrder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_order, null);

        ButterKnife.bind(this, view);
        mRvOrderList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvOrderList.setAdapter(new OrderAdapter(getActivity()));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int id = TakeOutApp.mUser.getId();
        if(id<0){
            Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        LoadOrdeInfo();
    }

    private void LoadOrdeInfo() {
    }
}
