package com.hhyg.TyClosing.util;

import com.hhyg.TyClosing.entities.order.Coupon;
import com.hhyg.TyClosing.entities.order.CouponBean;

/**
 * Created by chenqiyang on 2017/9/3.
 */

public class CouponUtil {
    public static void initCoupon(CouponBean res, Coupon coupon) {
        coupon.setCode_str(res.getCode_str());
        coupon.setConflict(res.getConflict());
        coupon.setDiscountDesc(res.getDiscountDesc());
        coupon.setUnavailableReason(res.getUnavailableReason());
        coupon.setName(res.getName());
        coupon.setIs_new(res.getIs_new());
        coupon.setIsEntire(res.getIsEntire());
        coupon.setEnd_time(res.getEnd_time());
        coupon.setStart_time(res.getStart_time());
        coupon.setReached_money(res.getReached_money());
        coupon.setReduce_money(res.getReduce_money());
        coupon.setTimeTv(TimeUtill.TimeStamp2Date(res.getStart_time()) + " ~ " + TimeUtill.TimeStamp2Date(res.getEnd_time()));
        coupon.setSpannableString(SpannableUtil.discountSpan(res.getReduce_money()));
        coupon.setNameTv("【" + res.getName() + "】" + res.getDiscountDesc());
    }
}
