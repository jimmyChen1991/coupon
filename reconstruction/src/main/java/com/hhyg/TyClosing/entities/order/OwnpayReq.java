package com.hhyg.TyClosing.entities.order;

import java.util.List;

/**
 * Created by chenqiyang on 2017/9/3.
 */

public class OwnpayReq {

    /**
     * shopid : 1
     * channel : 673
     * saleId : 8
     * imei : d30a10153d976f3a
     * platformId : 3
     * data : {"flightNum":"MF5054","giftcardPwd":"","giftcardCode":"","phone":"15010729526","couponCode":"","userName":"李飞","flightDate":"2017-09-03 20:10","deliverPlace":3,"giftcardKey":"","goodsSku":[{"number":"1","activity":"AI2017051114534340603","barcode":"7640127753927"}],"idCard":"451025198809227698","submitTime":1504401451893,"bonusNumber":"","deliverTime":"2017-09-03 10:02"}
     * device_type : android
     * op : owntopay
     */

    private String shopid;
    private String channel;
    private String saleId;
    private String imei;
    private String platformId;
    private DataBean data;
    private String device_type = "android";
    private String op = "owntopay";

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String  platformId) {
        this.platformId = platformId;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public static class DataBean {
        /**
         * flightNum : MF5054
         * giftcardPwd :
         * giftcardCode :
         * phone : 15010729526
         * couponCode :
         * userName : 李飞
         * flightDate : 2017-09-03 20:10
         * deliverPlace : 3
         * giftcardKey :
         * goodsSku : [{"number":"1","activity":"AI2017051114534340603","barcode":"7640127753927"}]
         * idCard : 451025198809227698
         * submitTime : 1504401451893
         * bonusNumber :
         * deliverTime : 2017-09-03 10:02
         */

        private String flightNum;
        private String giftcardPwd;
        private String giftcardCode;
        private String phone;
        private String couponCode;
        private String userName;
        private String flightDate;
        private int deliverPlace;
        private String giftcardKey;
        private String idCard;
        private long submitTime;
        private String bonusNumber;
        private String deliverTime;
        private List<GoodsSkuBean> goodsSku;
        private String token;

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public String getFlightNum() {
            return flightNum;
        }

        public void setFlightNum(String flightNum) {
            this.flightNum = flightNum;
        }

        public String getGiftcardPwd() {
            return giftcardPwd;
        }

        public void setGiftcardPwd(String giftcardPwd) {
            this.giftcardPwd = giftcardPwd;
        }

        public String getGiftcardCode() {
            return giftcardCode;
        }

        public void setGiftcardCode(String giftcardCode) {
            this.giftcardCode = giftcardCode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCouponCode() {
            return couponCode;
        }

        public void setCouponCode(String couponCode) {
            this.couponCode = couponCode;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFlightDate() {
            return flightDate;
        }

        public void setFlightDate(String flightDate) {
            this.flightDate = flightDate;
        }

        public int getDeliverPlace() {
            return deliverPlace;
        }

        public void setDeliverPlace(int deliverPlace) {
            this.deliverPlace = deliverPlace;
        }

        public String getGiftcardKey() {
            return giftcardKey;
        }

        public void setGiftcardKey(String giftcardKey) {
            this.giftcardKey = giftcardKey;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public long getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(long submitTime) {
            this.submitTime = submitTime;
        }

        public String getBonusNumber() {
            return bonusNumber;
        }

        public void setBonusNumber(String bonusNumber) {
            this.bonusNumber = bonusNumber;
        }

        public String getDeliverTime() {
            return deliverTime;
        }

        public void setDeliverTime(String deliverTime) {
            this.deliverTime = deliverTime;
        }

        public List<GoodsSkuBean> getGoodsSku() {
            return goodsSku;
        }

        public void setGoodsSku(List<GoodsSkuBean> goodsSku) {
            this.goodsSku = goodsSku;
        }

        public static class GoodsSkuBean {
            /**
             * number : 1
             * activity : AI2017051114534340603
             * barcode : 7640127753927
             */

            private int number;
            private String activity;
            private String barcode;

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getActivity() {
                return activity;
            }

            public void setActivity(String activity) {
                this.activity = activity;
            }

            public String getBarcode() {
                return barcode;
            }

            public void setBarcode(String barcode) {
                this.barcode = barcode;
            }
        }
    }
}
