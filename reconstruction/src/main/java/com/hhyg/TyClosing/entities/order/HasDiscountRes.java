package com.hhyg.TyClosing.entities.order;

/**
 * Created by user on 2017/9/1.
 */

public class HasDiscountRes {

    /**
     * errcode : 1
     * op : exchangecoupons
     * channel : hascouponsorcash
     * data : []
     * msg : 有可用的优惠券或者红包
     */

    private int errcode;
    private String msg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
