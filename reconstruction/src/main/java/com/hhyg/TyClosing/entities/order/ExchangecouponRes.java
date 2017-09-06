package com.hhyg.TyClosing.entities.order;

/**
 * Created by user on 2017/9/3.
 */

public class ExchangecouponRes {
    /*
    {
        "errcode": 1,  //1=》兑换成功，其他兑换失败
            "op": "exchangecoupons",
            "channel": "673",
            "data": {     //今日领取优惠券
        "coupons": {
            "USABLE": [
            {
                "is_new": 1,
                    "code_str": "842430381266705",
                    "reduce_money": 23,
                    "reached_money": 300,
                    "start_time_str": "2017-08-01",
                    "start_time": "1501517280",
                    "end_time_str": "2018-08-31",
                    "end_time": "1535677680",
                    "name": "优惠券005-01",
                    "description": "李敏渠道优惠券005",
                    "receiveTag": true,
                    "receiveTime": null,
                    "channelIds": null,
                    "discountDesc": "李敏渠道优惠券005",
                    "isEntire": 1,
                    "couponDescribe": null,
                    "goods": [
                {
                    "barCode": "3346470114746",
                        "num": 1,
                        "price": 830
                },
                {
                    "barCode": "027131826705",
                        "num": 1,
                        "price": 505
                }
                    ],
                "conflict": [
                "176010206570805",
                        "208211110235952",
                        "522933712741221",
                        "094464264473012",
                        "986132181237294",
                        "312843817962624"
                    ],
                "platformIds": "0,1,2,3",
                    "airportIds": "1,2,9,10",
                    "ruleDescribeStr": "300.00:23.00",
                    "unusableKey": null,
                    "unavailableReason": "",
                    "now_time": 1504603217,
                    "shopid": "1",
                    "platformid": "3",
                    "userid": "1177722"
            }
            ]
        }
    },
        "msg": "兑换成功"
    }
    /**
     * errcode : 1
     * op : exchangecoupons
     * channel : 673
     * msg : 兑换成功
     */

    private int errcode;
    private String msg;

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
}
