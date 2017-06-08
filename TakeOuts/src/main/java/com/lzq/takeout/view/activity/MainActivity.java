package com.lzq.takeout.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.lzq.takeout.R;
import com.lzq.takeout.view.fragments.HomeFragment;
import com.lzq.takeout.view.fragments.MoreFragment;
import com.lzq.takeout.view.fragments.OlderFragment;
import com.lzq.takeout.view.fragments.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    List<Fragment> mFragmentList = new ArrayList<>();

    @Bind(R.id.main_fragment_container)
    FrameLayout mMainFragmentContainer;
    @Bind(R.id.main_bottome_switcher_container)
    LinearLayout mMainBottomeSwitcherContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       hideBottomUIMenu();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
     mMainBottomeSwitcherContainer.setBackgroundColor(getResources().getColor(R.color.colorBottom));
        initFragment();
        initBottemNavigation();
        refretChildUiByIndex(0);
    }

    private void initBottemNavigation() {
        int childCount = mMainBottomeSwitcherContainer.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View childAt = mMainBottomeSwitcherContainer.getChildAt(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int indexOfChild = mMainBottomeSwitcherContainer.indexOfChild(childAt);
                    refretChildUiByIndex(indexOfChild);
                }
            });
        }
    }

    private void refretChildUiByIndex(int indexOfChild) {
        int childCount = mMainBottomeSwitcherContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = mMainBottomeSwitcherContainer.getChildAt(i);
            if (i == indexOfChild) {
                setEnable(childAt, false);
            } else {
                setEnable(childAt, true);
            }
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container,mFragmentList.get(indexOfChild)).commit();
    }

    private void setEnable(View childAt, boolean b) {
        childAt.setEnabled(b);
        if (childAt instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) childAt;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt1 = viewGroup.getChildAt(i);
             setEnable(childAt1, b);
            }

        }
    }

    private void initFragment() {
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new OlderFragment());
        mFragmentList.add(new UserFragment());
        mFragmentList.add(new MoreFragment());


    }
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ;
            decorView.setSystemUiVisibility(uiOptions);
            //| View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }
}
