package com.hhyg.TyClosing.entities.order;

/**
 * Created by user on 2017/9/3.
 */

public class ExchangecouponReq {

    /**
     * op : getcoupons
     * channel : 体验店渠道号
     * imei : imei号,可选（H5可不填）
     * shopid : 体验店ID
     * platformId : 客户端
     * data : {"exchangecode":"299337980453174"}
     */

    private String op;
    private String channel;
    private String imei;
    private String shopid;
    private String platformId;
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

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * exchangecode : 299337980453174
         */

        private String exchangecode;

        public String getExchangecode() {
            return exchangecode;
        }

        public void setExchangecode(String exchangecode) {
            this.exchangecode = exchangecode;
        }
    }
}
