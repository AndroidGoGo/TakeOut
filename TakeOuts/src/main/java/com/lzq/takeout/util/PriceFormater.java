package com.lzq.takeout.util;

import java.text.NumberFormat;

/**
 * Created by ${廖昭启} on 2017/6/11.
 */

public class PriceFormater {

    public static String format(float countPrice){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(2);
        return format.format(countPrice);
    }
}
