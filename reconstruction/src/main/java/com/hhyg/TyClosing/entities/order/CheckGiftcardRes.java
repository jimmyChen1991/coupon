package com.hhyg.TyClosing.entities.order;

/**
 * Created by chenqiyang on 2017/9/2.
 */

public class CheckGiftcardRes {

    /**
     * errcode : 1
     * op : giftcardcheckin
     * channel : 673
     * data : {"checkFlag":0,"barcode":"3486296095304561962"}
     * msg : 操作成功
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
         * checkFlag : 0
         * barcode : 3486296095304561962
         */

        private int checkFlag;
        private String barcode;

        public int getCheckFlag() {
            return checkFlag;
        }

        public void setCheckFlag(int checkFlag) {
            this.checkFlag = checkFlag;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }
    }
}
