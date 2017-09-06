package com.hhyg.TyClosing.entities.order;

import java.util.List;

/**
 * Created by user on 2017/9/5.
 */

public class AutodataReq {
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
    private int needcheckcode;
    private List<GoodslistBean> goodslist;

    public void setNeedcheckcode(int needcheckcode) {
        this.needcheckcode = needcheckcode;
    }

    public int getNeedcheckcode() {
        return needcheckcode;
    }

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
