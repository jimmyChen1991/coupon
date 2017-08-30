package com.hhyg.TyClosing.entities.order;

import com.hhyg.TyClosing.entities.CommonParam;

/**
 * Created by user on 2017/8/29.
 */

public class SendVaildateCodeReq {

    /**
     * op : send
     * channel : 体验店渠道号
     * imei : imei号,可选（H5可不填）
     * shopid : 体验店ID
     * data : {"mobilephone":"手机号码","timestamp":"时间戳（验证短信中接口获取）","sign":"签名（验证短信中接口获取）"}
     */

    private String op;
    private String channel;
    private String imei;
    private String shopid;
    private DataBean data;

    public SendVaildateCodeReq(CommonParam param) {
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
         * timestamp : 时间戳（验证短信中接口获取）
         * sign : 签名（验证短信中接口获取）
         */

        private String mobilephone;
        private String timestamp;
        private String sign;

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
