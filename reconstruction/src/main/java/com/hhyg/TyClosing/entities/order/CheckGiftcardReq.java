package com.hhyg.TyClosing.entities.order;

/**
 * Created by chenqiyang on 2017/9/2.
 */

public class CheckGiftcardReq {

    /**
     * device_type : android
     * platformId : 3
     * imei : d30a10153d976f3a
     * data : {"giftKey":"d30a10153d976f3a_1504357010","giftCode":"3486296095304561962","checkFlag":"0"}
     * shopid : 1
     * op : giftcardcheckin
     * channel : 673
     * saleId : 8
     */

    private String device_type = "android";
    private String platformId;
    private String imei;
    private DataBean data;
    private String shopid;
    private String op = "giftcardcheckin";
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
         * giftKey : d30a10153d976f3a_1504357010
         * giftCode : 3486296095304561962
         * checkFlag : 0
         */

        private String giftKey;
        private String giftCode;
        private int checkFlag;

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

        public int getCheckFlag() {
            return checkFlag;
        }

        public void setCheckFlag(int checkFlag) {
            this.checkFlag = checkFlag;
        }
    }
}
