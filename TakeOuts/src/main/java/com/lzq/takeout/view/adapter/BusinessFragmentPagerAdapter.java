package com.lzq.takeout.view.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ${廖昭启} on 2017/6/8.
 */

public class BusinessFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmetlists ;
    private String[] mTitle = new String[]{"商品","评论","商家"};

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    public BusinessFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        mFragmetlists= fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmetlists==null?null:mFragmetlists.get(position);
    }

    @Override
    public int getCount() {
        return mFragmetlists==null?0:mFragmetlists.size();
    }
}
