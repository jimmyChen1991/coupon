package com.hhyg.TyClosing.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;

import com.hhyg.TyClosing.config.Constants;

/**
 * Created by chenqiyang on 2017/9/2.
 */

public class SpannableUtil {

    public static SpannableString discountSpan(double money){
        SpannableString spannableString = new SpannableString(Constants.PRICE_TITLE + String.valueOf((int)money));
        spannableString.setSpan(new AbsoluteSizeSpan(25), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE );
        return spannableString;
    }
}
