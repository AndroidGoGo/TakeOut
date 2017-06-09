package com.lzq.takeout.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzq.takeout.R;
import com.lzq.takeout.model.bean.Seller;
import com.lzq.takeout.view.adapter.BusinessFragmentPagerAdapter;
import com.lzq.takeout.view.fragments.CommentsFragment;
import com.lzq.takeout.view.fragments.GoodsFragment;
import com.lzq.takeout.view.fragments.SellerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${廖昭启} on 2017/6/8.
 */

public class BusinessActivity extends Activity {
    @Bind(R.id.ib_back)
    ImageButton mIbBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.ib_menu)
    ImageButton mIbMenu;
    @Bind(R.id.vp)
    ViewPager mVp;
    @Bind(R.id.bottomSheetLayout)
    com.flipboard.bottomsheet.BottomSheetLayout mBottomSheetLayout;
    @Bind(R.id.imgCart)
    ImageView mImgCart;
    @Bind(R.id.tvSelectNum)
    TextView mTvSelectNum;
    @Bind(R.id.tvCountPrice)
    TextView mTvCountPrice;
    @Bind(R.id.tvSendPrice)
    TextView mTvSendPrice;
    @Bind(R.id.tvDeliveryFee)
    TextView mTvDeliveryFee;
    @Bind(R.id.tvSubmit)
    TextView mTvSubmit;
    @Bind(R.id.bottom)
    LinearLayout mBottom;
    @Bind(R.id.fl_Container)
    FrameLayout mFlContainer;
    @Bind(R.id.tabs)
    TabLayout mTabs;
    public Seller mSeller;
List<Fragment> mFragmentList   = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        ButterKnife.bind(this);
        initFragment();
        BusinessFragmentPagerAdapter businessFragmentPagerAdapter = new BusinessFragmentPagerAdapter(getFragmentManager());
        businessFragmentPagerAdapter.setFragmentList(mFragmentList);
        mVp.setAdapter(businessFragmentPagerAdapter);
        mTabs.setupWithViewPager(mVp);

    }
    @OnClick({R.id.ib_back, R.id.tvSubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.tvSubmit:
                //结算按钮
                break;
        }
    }

    private void initFragment() {
        mFragmentList.add(new GoodsFragment());
        mFragmentList.add(new CommentsFragment());
        mFragmentList.add(new SellerFragment() );
    }


}
