package com.hhyg.TyClosing.entities.order;

/**
 * Created by user on 2017/8/31.
 */

public class SearchGiftCardReq {

    /**
     * device_type : android
     * platformId : 3
     * imei : d30a10153d976f3a
     * data : {"orderPrice":"8545.00","giftKey":"","giftCode":"2908980100000855803","giftPwd":"JqctQ3oyLvM="}
     * shopid : 1
     * op : getgiftcard
     * channel : 673
     * saleId : 8
     */

    private String device_type;
    private String platformId;
    private String imei;
    private DataBean data;
    private String shopid;
    private String op;
    private String channel;
    private String saleId;

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
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

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public static class DataBean {
        /**
         * orderPrice : 8545.00
         * giftKey :
         * giftCode : 2908980100000855803
         * giftPwd : JqctQ3oyLvM=
         */

        private String orderPrice;
        private String giftKey;
        private String giftCode;
        private String giftPwd;

        public String getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(String orderPrice) {
            this.orderPrice = orderPrice;
        }

        public String getGiftKey() {
            return giftKey;
        }

        public void setGiftKey(String giftKey) {
            this.giftKey = giftKey;
        }

        public String getGiftCode() {
            return giftCode;
        }

        public void setGiftCode(String giftCode) {
            this.giftCode = giftCode;
        }

        public String getGiftPwd() {
            return giftPwd;
        }

        public void setGiftPwd(String giftPwd) {
            this.giftPwd = giftPwd;
        }
    }
}
