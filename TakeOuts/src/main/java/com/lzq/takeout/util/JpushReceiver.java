package com.lzq.takeout.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ${廖昭启} on 2017/6/8.
 */

public class JpushReceiver  extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        OrderObservable.getInstance().newMsgComing(extra);
    }
}
