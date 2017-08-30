package com.hhyg.TyClosing.entities.order;

/**
 * Created by user on 2017/8/29.
 */

public class SecuryRes {


    /**
     * errcode : 1表示成功，其他表示失败
     * op : secury
     * channel : 体验店渠道号
     * shopid : 体验店ID
     * data : {"timestamp":"时间戳","sign":"签名"}
     * msg : 成功
     */

    private String errcode;
    private String op;
    private String channel;
    private String shopid;
    private DataBean data;
    private String msg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * timestamp : 时间戳
         * sign : 签名
         */

        private String timestamp;
        private String sign;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
