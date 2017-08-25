package com.hhyg.TyClosing.entities.order;

import java.util.List;

/**
 * Created by user on 2017/8/25.
 */

public class VaildateInfo {

    /**
     * goodsPrice : 32
     * deliverTime : 2017-08-25 11:12
     * SpecialCount : 0
     * token : BDdWZQRnATUANFo8AWFUagRhUDcDMVt6XG0MPQg7XmpXYQE2VjVRKA==
     * phone : 15010729526
     * goodsSku : [{"number":1,"goods_name":"Tivoli牛奶黑巧克力曲奇饼 150g","tax_display_txt":"","goods_price":"32","activity":"-1","barcode":"5776879010970","brand_name":"Jacobsens杰克布森","goods_img":"https://img.mianshui365.com/upload/20151023/16555210717.jpg@300h_300w_95q_1wh","goods_attr":"150g"}]
     * everyFullActive : 0
     * discountActive : 0
     * everyDiscountActive : 0
     * finalPrice : 32
     * money_rate : 0
     * available : 0
     * userInfo : {"flightNum":"MF5054","idCard":"451025198809227698","flightDate":"2017-08-25 20:10","phone":"15010729526","userName":"李飞"}
     * fullActive : 0
     */

    private String goodsPrice;
    private String deliverTime;
    private int SpecialCount;
    private String token;
    private String phone;
    private String everyFullActive;
    private String discountActive;
    private String everyDiscountActive;
    private String finalPrice;
    private String money_rate;
    private int available;
    private UserInfoBean userInfo;
    private String fullActive;
    private List<GoodsSkuBean> goodsSku;

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public int getSpecialCount() {
        return SpecialCount;
    }

    public void setSpecialCount(int SpecialCount) {
        this.SpecialCount = SpecialCount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEveryFullActive() {
        return everyFullActive;
    }

    public void setEveryFullActive(String everyFullActive) {
        this.everyFullActive = everyFullActive;
    }

    public String getDiscountActive() {
        return discountActive;
    }

    public void setDiscountActive(String discountActive) {
        this.discountActive = discountActive;
    }

    public String getEveryDiscountActive() {
        return everyDiscountActive;
    }

    public void setEveryDiscountActive(String everyDiscountActive) {
        this.everyDiscountActive = everyDiscountActive;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getMoney_rate() {
        return money_rate;
    }

    public void setMoney_rate(String money_rate) {
        this.money_rate = money_rate;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public String getFullActive() {
        return fullActive;
    }

    public void setFullActive(String fullActive) {
        this.fullActive = fullActive;
    }

    public List<GoodsSkuBean> getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(List<GoodsSkuBean> goodsSku) {
        this.goodsSku = goodsSku;
    }

    public static class UserInfoBean {
        /**
         * flightNum : MF5054
         * idCard : 451025198809227698
         * flightDate : 2017-08-25 20:10
         * phone : 15010729526
         * userName : 李飞
         */

        private String flightNum;
        private String idCard;
        private String flightDate;
        private String phone;
        private String userName;

        public String getFlightNum() {
            return flightNum;
        }

        public void setFlightNum(String flightNum) {
            this.flightNum = flightNum;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getFlightDate() {
            return flightDate;
        }

        public void setFlightDate(String flightDate) {
            this.flightDate = flightDate;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class GoodsSkuBean {
        /**
         * number : 1
         * goods_name : Tivoli牛奶黑巧克力曲奇饼 150g
         * tax_display_txt :
         * goods_price : 32
         * activity : -1
         * barcode : 5776879010970
         * brand_name : Jacobsens杰克布森
         * goods_img : https://img.mianshui365.com/upload/20151023/16555210717.jpg@300h_300w_95q_1wh
         * goods_attr : 150g
         */

        private int number;
        private String goods_name;
        private String tax_display_txt;
        private String goods_price;
        private String activity;
        private String barcode;
        private String brand_name;
        private String goods_img;
        private String goods_attr;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getTax_display_txt() {
            return tax_display_txt;
        }

        public void setTax_display_txt(String tax_display_txt) {
            this.tax_display_txt = tax_display_txt;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
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

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(String goods_attr) {
            this.goods_attr = goods_attr;
        }
    }
}
