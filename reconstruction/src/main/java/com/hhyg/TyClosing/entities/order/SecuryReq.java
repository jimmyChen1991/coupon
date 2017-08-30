package com.hhyg.TyClosing.entities.order;

import com.hhyg.TyClosing.entities.CommonParam;

/**
 * Created by user on 2017/8/29.
 */

public class SecuryReq {

    /**
     * op : secury
     * channel : 体验店渠道号
     * imei : imei号,可选（H5可不填）
     * shopid : 体验店ID
     * data : {"mobilephone":"手机号码"}
     */

    private String op;
    private String channel;
    private String imei;
    private String shopid;
    private DataBean data;

    public SecuryReq(CommonParam param) {
        this.channel = param.getChannelId();
        this.imei = param.getImei();
        this.shopid = param.getShopId();
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
         * mobilephone : 手机号码
         */

        private String mobilephone;

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }
    }
}
