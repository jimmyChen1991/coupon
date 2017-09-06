package com.hhyg.TyClosing.entities.order;

import java.util.List;

/**
 * Created by user on 2017/9/1.
 */

public class DiscountReq {

    /**
     * op : exchangecoupons
     * channel : 673
     * platformId : 1
     * imei : 98bce97b104a4993
     * shopid : 1
     * data : {"goodslist":[{"barcode":"085715061508","num":10}],"deliverplace":3,"final_total_price":2000,"mobile_phone":"13138964919","code":"验证码","token":"加密串，（资格验证后获取）"}
     */

    private String op = "getdiscount";
    private String channel;
    private String platformId;
    private String imei;
    private String shopid;
    private AutodataReq data;

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

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
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

    public AutodataReq getData() {
        return data;
    }

    public void setData(AutodataReq data) {
        this.data = data;
    }

}
