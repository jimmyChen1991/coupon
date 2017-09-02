package com.hhyg.TyClosing.entities.order;

/**
 * Created by user on 2017/9/1.
 */

public class HasDiscountReq {

    /**
     * op : hascouponsorcash
     * channel : 体验店渠道号
     * imei : imei号,可选（H5可不填）
     * shopid : 体验店ID
     * data : {"mobile_phone":"手机号","userCart":{"sign":"秘钥","userId":"用户id"}}
     */

    private String op = "hascouponsorcash";
    private String channel;
    private String imei;
    private String shopid;
    private DataBean data;

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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mobile_phone : 手机号
         * userCart : {"sign":"秘钥","userId":"用户id"}
         */

        private String mobile_phone;

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }
    }
}
