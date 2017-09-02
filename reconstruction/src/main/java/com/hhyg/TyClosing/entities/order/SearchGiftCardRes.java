package com.hhyg.TyClosing.entities.order;

/**
 * Created by user on 2017/8/31.
 */

public class SearchGiftCardRes {


    /**
     * errcode : 1
     * msg : 获取礼品卡成功
     * op : getgiftcard
     * channel : 673
     * data : {"barcode":"5362123686915368065","temp_order_key":"d30a10153d976f3a_1504232728","money":1000,"time_begin":"1478188800","time_end":"1527177599","detail":"1000元礼品卡（卡号：5362123686915368065）"}
     */

    private int errcode;
    private String msg;
    private String op;
    private int channel;
    private DataBean data;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * barcode : 5362123686915368065
         * temp_order_key : d30a10153d976f3a_1504232728
         * money : 1000
         * time_begin : 1478188800
         * time_end : 1527177599
         * detail : 1000元礼品卡（卡号：5362123686915368065）
         */

        private String barcode;
        private String temp_order_key;
        private double money;
        private String time_begin;
        private String time_end;
        private String detail;

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getTemp_order_key() {
            return temp_order_key;
        }

        public void setTemp_order_key(String temp_order_key) {
            this.temp_order_key = temp_order_key;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getTime_begin() {
            return time_begin;
        }

        public void setTime_begin(String time_begin) {
            this.time_begin = time_begin;
        }

        public String getTime_end() {
            return time_end;
        }

        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
