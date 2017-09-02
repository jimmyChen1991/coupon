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
        String res = "";
        if(!TextUtils.isEmpty(timestampString)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            long lt = new Long(timestampString);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
        }
        return res;

    }
}
