package com.lzq.takeout.view.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.lzq.takeout.R;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${廖昭启} on 2017/6/5.
 */

public class TitleHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "TitleHolder";
    @Bind(R.id.slider)
    SliderLayout mSlider;


    public TitleHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void setDatas(String s) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
       map.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        map.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        map.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        for (String dec:map.keySet()){
            Log.d(TAG,"加载轮播图");
            TextSliderView sliderView = new TextSliderView(itemView.getContext());
            sliderView.description(dec).image(map.get(dec));
            mSlider.addSlider(sliderView);
        }

    }
}
