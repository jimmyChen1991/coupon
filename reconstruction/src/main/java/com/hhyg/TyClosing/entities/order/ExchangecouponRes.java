package com.hhyg.TyClosing.entities.order;

import java.util.List;

/**
 * Created by user on 2017/9/3.
 */

public class ExchangecouponRes {

    /**
     * errcode : 1
     * op : exchangecoupons
     * channel : 673
     * data : {"coupons":{"USABLE":[{"is_new":1,"code_str":"842430381266705","reduce_money":23,"reached_money":300,"start_time_str":"2017-08-01","start_time":"1501517280","end_time_str":"2018-08-31","end_time":"1535677680","name":"优惠券005-01","description":"李敏渠道优惠券005","receiveTag":true,"receiveTime":null,"channelIds":null,"discountDesc":"李敏渠道优惠券005","isEntire":1,"couponDescribe":null,"goods":[{"barCode":"3346470114746","num":1,"price":830},{"barCode":"027131826705","num":1,"price":505}],"conflict":["176010206570805","208211110235952","522933712741221","094464264473012","986132181237294","312843817962624"],"platformIds":"0,1,2,3","airportIds":"1,2,9,10","ruleDescribeStr":"300.00:23.00","unusableKey":null,"unavailableReason":"","now_time":1504603217,"shopid":"1","platformid":"3","userid":"1177722"}]}}
     * msg : 兑换成功
     */

    private int errcode;
    private String op;
    private String channel;
    private DataBean data;
    private String msg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * coupons : {"USABLE":[{"is_new":1,"code_str":"842430381266705","reduce_money":23,"reached_money":300,"start_time_str":"2017-08-01","start_time":"1501517280","end_time_str":"2018-08-31","end_time":"1535677680","name":"优惠券005-01","description":"李敏渠道优惠券005","receiveTag":true,"receiveTime":null,"channelIds":null,"discountDesc":"李敏渠道优惠券005","isEntire":1,"couponDescribe":null,"goods":[{"barCode":"3346470114746","num":1,"price":830},{"barCode":"027131826705","num":1,"price":505}],"conflict":["176010206570805","208211110235952","522933712741221","094464264473012","986132181237294","312843817962624"],"platformIds":"0,1,2,3","airportIds":"1,2,9,10","ruleDescribeStr":"300.00:23.00","unusableKey":null,"unavailableReason":"","now_time":1504603217,"shopid":"1","platformid":"3","userid":"1177722"}]}
         */

        private CouponsBean coupons;

        public CouponsBean getCoupons() {
            return coupons;
        }

        public void setCoupons(CouponsBean coupons) {
            this.coupons = coupons;
        }

        public static class CouponsBean {
            private List<CouponBean> USABLE;
            private List<CouponBean> UNUSABLE;

            public void setUNUSABLE(List<CouponBean> UNUSABLE) {
                this.UNUSABLE = UNUSABLE;
            }

            public List<CouponBean> getUNUSABLE() {
                return UNUSABLE;
            }

            public List<CouponBean> getUSABLE() {
                return USABLE;
            }

            public void setUSABLE(List<CouponBean> USABLE) {
                this.USABLE = USABLE;
            }

        }
    }
}
