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

    private String op = "exchangecoupons";
    private String channel;
    private String platformId;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goodslist : [{"barcode":"085715061508","num":10}]
         * deliverplace : 3
         * final_total_price : 2000
         * mobile_phone : 13138964919
         * code : 验证码
         * token : 加密串，（资格验证后获取）
         */

        private String deliverplace;
        private String final_total_price;
        private String mobile_phone;
        private String code;
        private String token;
        private List<GoodslistBean> goodslist;

        public String getDeliverplace() {
            return deliverplace;
        }

        public void setDeliverplace(String deliverplace) {
            this.deliverplace = deliverplace;
        }

        public String getFinal_total_price() {
            return final_total_price;
        }

        public void setFinal_total_price(String final_total_price) {
            this.final_total_price = final_total_price;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            /**
             * barcode : 085715061508
             * num : 10
             */

            private String barcode;
            private int num;

            public String getBarcode() {
                return barcode;
            }

            public void setBarcode(String barcode) {
                this.barcode = barcode;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }
    }
}
