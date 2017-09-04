package com.hhyg.TyClosing.entities.order;

/**
 * Created by user on 2017/9/3.
 */

public class ExchangecouponRes {

    /**
     * errcode : 1
     * op : exchangecoupons
     * channel : 673
     * msg : 兑换成功
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
