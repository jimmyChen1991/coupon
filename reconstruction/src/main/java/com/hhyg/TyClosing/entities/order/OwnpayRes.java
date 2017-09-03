package com.hhyg.TyClosing.entities.order;

/**
 * Created by chenqiyang on 2017/9/3.
 */

public class OwnpayRes {

    /**
     * errcode : 1
     * op : owntopay
     * data : {"cartSn":"2017090380630","wxPayUrl":"http://pay.mianshui365.net/?_c=pay&_a=index&_t=1504401449&ext_params=eyJvcmRlcl9zbiI6IjIwMTcwOTAzMzE3OTAiLCJvcGVyYXRlIjoic2V0dGxlVGFiIiwib3JkZXJfcHJpY2UiOjI5OTEsInBheV90eXBlIjoxNTMsIlNldHRsZVRhYklEIjoxfQ==&pay_type=weixin&operte=settleTab&isValidated=1","tip":"请于45分钟内完成支付，否则订单将会自动取消。","alipayUrl":"http://pay.mianshui365.net/?_c=pay&_a=index&_t=1504401449&ext_params=eyJvcmRlcl9zbiI6IjIwMTcwOTAzMzE3OTAiLCJvcGVyYXRlIjoic2V0dGxlVGFiIiwib3JkZXJfcHJpY2UiOjI5OTEsInBheV90eXBlIjozLCJTZXR0bGVUYWJJRCI6MX0=&pay_type=alipay&operte=settleTab&isValidated=1","successPayUrl":"","msg":"订单提交成功，请您尽快付款！ 订单号：2017090331790","orderSn":"2017090331790","citOrderSn":"2017090300031890","alert":"","finalPrice":2991,"SpecialCount":0}
     * msg : 订单提交成功，请您尽快付款！ 订单号：2017090331790
     * channel : 673
     */

    private int errcode;
    private DataBean data;
    private String msg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * cartSn : 2017090380630
         * wxPayUrl : http://pay.mianshui365.net/?_c=pay&_a=index&_t=1504401449&ext_params=eyJvcmRlcl9zbiI6IjIwMTcwOTAzMzE3OTAiLCJvcGVyYXRlIjoic2V0dGxlVGFiIiwib3JkZXJfcHJpY2UiOjI5OTEsInBheV90eXBlIjoxNTMsIlNldHRsZVRhYklEIjoxfQ==&pay_type=weixin&operte=settleTab&isValidated=1
         * tip : 请于45分钟内完成支付，否则订单将会自动取消。
         * alipayUrl : http://pay.mianshui365.net/?_c=pay&_a=index&_t=1504401449&ext_params=eyJvcmRlcl9zbiI6IjIwMTcwOTAzMzE3OTAiLCJvcGVyYXRlIjoic2V0dGxlVGFiIiwib3JkZXJfcHJpY2UiOjI5OTEsInBheV90eXBlIjozLCJTZXR0bGVUYWJJRCI6MX0=&pay_type=alipay&operte=settleTab&isValidated=1
         * successPayUrl :
         * msg : 订单提交成功，请您尽快付款！ 订单号：2017090331790
         * orderSn : 2017090331790
         * citOrderSn : 2017090300031890
         * alert :
         * finalPrice : 2991
         * SpecialCount : 0
         */

        private String cartSn;
        private String wxPayUrl;
        private String tip;
        private String alipayUrl;
        private String successPayUrl;
        private String msg;
        private String orderSn;
        private String citOrderSn;
        private String alert;
        private String finalPrice;
        private int SpecialCount;

        public String getCartSn() {
            return cartSn;
        }

        public void setCartSn(String cartSn) {
            this.cartSn = cartSn;
        }

        public String getWxPayUrl() {
            return wxPayUrl;
        }

        public void setWxPayUrl(String wxPayUrl) {
            this.wxPayUrl = wxPayUrl;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public String getAlipayUrl() {
            return alipayUrl;
        }

        public void setAlipayUrl(String alipayUrl) {
            this.alipayUrl = alipayUrl;
        }

        public String getSuccessPayUrl() {
            return successPayUrl;
        }

        public void setSuccessPayUrl(String successPayUrl) {
            this.successPayUrl = successPayUrl;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public String getCitOrderSn() {
            return citOrderSn;
        }

        public void setCitOrderSn(String citOrderSn) {
            this.citOrderSn = citOrderSn;
        }

        public String getAlert() {
            return alert;
        }

        public void setAlert(String alert) {
            this.alert = alert;
        }

        public String getFinalPrice() {
            return finalPrice;
        }

        public void setFinalPrice(String finalPrice) {
            this.finalPrice = finalPrice;
        }

        public int getSpecialCount() {
            return SpecialCount;
        }

        public void setSpecialCount(int SpecialCount) {
            this.SpecialCount = SpecialCount;
        }
    }
}
