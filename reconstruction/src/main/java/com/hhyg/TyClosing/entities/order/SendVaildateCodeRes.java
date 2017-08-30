package com.hhyg.TyClosing.entities.order;

/**
 * Created by user on 2017/8/29.
 */

public class SendVaildateCodeRes {

    /**
     * channel : 840
     * shopid : 6
     * op : send
     * data : {"mobilephone":"15xxxxxxxxx","timestamp":1491037696,"sign":"xxxxxxxxxxxxxxxxxxxx"}
     */

    private int channel;
    private String op;
    private String msg;
    private int errcode;
    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public int getErrcode() {
        return errcode;
    }
}
