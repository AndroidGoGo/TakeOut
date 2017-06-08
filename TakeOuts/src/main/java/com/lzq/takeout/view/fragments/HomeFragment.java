package com.lzq.takeout.view.fragments;

import android.animation.ArgbEvaluator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.Seller;
import com.lzq.takeout.presenter.HomeFragmentPresenter;
import com.lzq.takeout.view.adapter.HomeAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${廖昭启} on 2017/6/5.
 */

public class HomeFragment extends Fragment {
    @Bind(R.id.rv_home)
    RecyclerView mRvHome;
    @Bind(R.id.home_tv_address)
    TextView mHomeTvAddress;
    @Bind(R.id.ll_title_search)
    LinearLayout mLlTitleSearch;
    @Bind(R.id.ll_title_container)
    LinearLayout mLlTitleContainer;

    int sumY = 0;
    float distance = 150;  //最远滚动距离，超过这个距离还是最蓝色
    int startColor = 0x553190E8;
    int endColor = 0xff3190E8;
    ArgbEvaluator mEvaluator = new ArgbEvaluator();
    private HomeFragmentPresenter mHomeFragmentPresenter;
    private HomeAdapter mHomeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        mRvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeFragmentPresenter = new HomeFragmentPresenter(this);
        mHomeAdapter = new HomeAdapter(getActivity());
        mRvHome.setAdapter(mHomeAdapter);
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
        mHomeFragmentPresenter.loadMore();
        mRvHome.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sumY += dy;
                int bgColor;
                if (sumY < 0) {
                    //初始最浅的蓝色0x553190E8
                    bgColor = startColor;
                } else if (sumY > distance) {
                    //最蓝的颜色0xff3190E8
                    bgColor = endColor;
                } else {
                    //在0-distance范围内计算alpha的值  255
                    bgColor = (int) mEvaluator.evaluate(sumY / distance, startColor, endColor);
                }
                mLlTitleContainer.setBackgroundColor(bgColor);
            }
        });
    }

    public void onSuccess(List<Seller> nearbySellerList, List<Seller> otherSellerList) {

        mHomeAdapter.setDatas(nearbySellerList, otherSellerList);
    }

    public void onSuccessBug() {

    }

    public void onFailed() {

    }
}
