package com.hhyg.TyClosing.entities.order;

import java.util.List;

/**
 * Created by user on 2017/9/3.
 */

public class ExchangecouponReq {


    /**
     * channel : 673
     * data : {"deliverplace":"3","final_total_price":"1335","exchangecode":"842430381266705","goodslist":[{"barcode":"3346470114746","num":1},{"barcode":"027131826705","num":1}],"mobile_phone":"13208902327","token":"UGNQZQBhAzYEOFA4A2FUYVIxWzxQYwgpDTwANV5jDDcIPVdjVTRTKg=="}
     * imei : 11391e0ce75b9f42
     * op : exchangecoupons
     * platformId : 3
     * shopid : 1
     */

    private String channel;
    private DataBean data;
    private String imei;
    private String op;
    private String platformId;
    private String shopid;

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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public static class DataBean extends AutodataReq{

        private String exchangecode;

        public void setExchangecode(String exchangecode) {
            this.exchangecode = exchangecode;
        }

        public String getExchangecode() {
            return exchangecode;
        }
    }
}
