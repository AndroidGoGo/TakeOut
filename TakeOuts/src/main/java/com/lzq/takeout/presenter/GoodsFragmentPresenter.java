package com.lzq.takeout.presenter;

import android.widget.AbsListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzq.takeout.model.bean.GoodsInfo;
import com.lzq.takeout.model.bean.GoodsTypeInfo;
import com.lzq.takeout.model.bean.ResponseInfo;
import com.lzq.takeout.view.fragments.GoodsFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by ${廖昭启} on 2017/6/8.
 */

public class GoodsFragmentPresenter extends NetPresenter{
    private GoodsFragment mGoodsFragment;
    private List<GoodsInfo> mAllTypeGoodsList;
    public List<GoodsTypeInfo> mGoodsTypeInfoList;
    public GoodsFragmentPresenter(GoodsFragment goodsFragment){
        mGoodsFragment=goodsFragment;
    }
    @Override
    protected void onFailRespone(String message) {

    }

    @Override
    protected void OnSuccessBug(int code) {

    }

    @Override
    protected void OnSuccess(String data) {
        mAllTypeGoodsList = new ArrayList<>();
        //可以解析了
        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject(data);
            String listStr = jsonObject.getString("list");
            //List<GoodsTypeInfo>
            mGoodsTypeInfoList = gson.fromJson(listStr, new TypeToken<List<GoodsTypeInfo>>() {
            }.getType());
            mGoodsFragment.onGoodsTypeSuccess(mGoodsTypeInfoList);

            //取出每个类别的商品
            for (int i = 0; i < mGoodsTypeInfoList.size(); i++) {
                GoodsTypeInfo goodsTypeInfo = mGoodsTypeInfoList.get(i); //饮料类别
                List<GoodsInfo> list = goodsTypeInfo.getList();//可乐 王老吉 橙汁
                for (int j = 0; j < list.size(); j++) {
                    GoodsInfo goodsInfo = list.get(j);
                    //TODO：获取到数据时给孩子（商品）设置外键（属于哪个类别）？
                    goodsInfo.setTypeId(goodsTypeInfo.getId());
                    goodsInfo.setTypeName(goodsTypeInfo.getName());
                    mAllTypeGoodsList.add(goodsInfo);
                }
            }
            mGoodsFragment.onAllGoodsSuccess(mAllTypeGoodsList);
            //有数据后添加滚动
            mGoodsFragment.mSlhlv.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    //firstVisibleItem第一个可见的条目
                    //TODO:新的条目成为firstVisibleItem，顶掉旧的条目时，切换类别
                    int oldIndex = mGoodsFragment.mGoodsTypeRVAdapter.selectIndex;

                    int newTypeId = mAllTypeGoodsList.get(firstVisibleItem).getTypeId();
                    int newIndex = getTypeIndexByTypeId(newTypeId);

                    if (newIndex != oldIndex) {
                        //切换了类别
                        mGoodsFragment.mGoodsTypeRVAdapter.selectIndex = newIndex;
                        //刷新左侧列表
                        mGoodsFragment.mGoodsTypeRVAdapter.notifyDataSetChanged();
                    }


                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
//TODO:
    private int getTypeIndexByTypeId(int newTypeId) {
        return 0;
    }

    public void loadBussinessInfo(long id) {
        Call<ResponseInfo> infoCall = mTakeOutService.loadBusinesInfo((int) id);
        infoCall.enqueue(mCallback);

    }
}
