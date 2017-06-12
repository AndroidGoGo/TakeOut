package com.lzq.takeout.view.fragments;

import android.app.Fragment;
import android.content.Context;
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
import com.lzq.takeout.model.bean.Order;
import com.lzq.takeout.presenter.OrderFragmentPresenter;
import com.lzq.takeout.view.adapter.OrderAdapter;

import java.util.List;

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
    public OrderFragmentPresenter mOrderFragmentPresenter;
   // private OrderRvAdapter mOrderRvAdapter;
    private OrderAdapter mOrderAdapter;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_order, null);
mContext =getActivity();
        ButterKnife.bind(this, view);
        mOrderFragmentPresenter = new OrderFragmentPresenter(this);
        mRvOrderList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvOrderList.setAdapter(new OrderAdapter(mContext));
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
        if (id < 0) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        mOrderFragmentPresenter.getOrderList(id + "");
    }

    public void LoadOrdeInfo(List<Order> orderList) {
       mOrderAdapter.setOrderList(orderList);

        mSrlOrder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO:下拉松开后的回调，可以去加载最新数据
                int id = TakeOutApp.mUser.getId();

                //再次请求服务器数据
                mOrderFragmentPresenter.getOrderList(id + "");

                mSrlOrder.setRefreshing(false);  //关闭转圈圈
            }
        });
    }
}
