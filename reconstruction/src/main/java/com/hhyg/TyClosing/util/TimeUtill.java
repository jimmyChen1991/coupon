package com.hhyg.TyClosing.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by user on 2017/9/1.
 */

public class TimeUtill {
    public static String TimeStamp2Date(String timestampString) {
        String date = "";
        if(!TextUtils.isEmpty(timestampString)){
            Long timestamp = Long.parseLong(timestampString)*1000;
            date = new java.text.SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date(timestamp));
        }
        return date;

    }
}
