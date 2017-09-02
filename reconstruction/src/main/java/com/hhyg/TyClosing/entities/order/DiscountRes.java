package com.hhyg.TyClosing.entities.order;

import java.util.List;

/**
 * Created by user on 2017/9/1.
 */

public class DiscountRes {

    /**
     * errcode : 1
     * op : getdiscount
     * channel : hascouponsorcash
     * data : {"coupons":{"USABLE":[{"is_new":0,"code_str":"88285964","reduce_money":10,"reached_money":100,"isEntire":1,"start_time_str":"2017-07-25","start_time":"1500912000","end_time_str":"2017-08-31","end_time":"1504195199","name":"XLP测试","description":"满100元减10元","partner_id":"101","unusableKey":null,"unavailableReason":"","now_time":1504158538},{"is_new":1,"code_str":"068372754539013","reduce_money":20,"reached_money":200,"start_time_str":"2017-08-25","start_time":"1503645060","end_time_str":"2017-08-31","end_time":"1504163460","name":"品牌优惠券","description":"1.适用店铺：免税易购官网、海口机场体验店、三亚机场体验店\r\n2.适用平台：全平台\r\n3.适用离岛地点：所有离岛地点\r\n4.适用商品：限兰芝、安娜苏、娇韵诗品牌商品使用\r\n5.特例商品：无","receiveTag":true,"channelIds":"","discountDesc":"仅可购买兰芝、安娜苏、娇韵诗品牌商品","isEntire":1,"couponDescribe":null,"goods":[{"barCode":"085715061508","num":10,"price":400}],"conflict":["068372754539013"],"platformIds":"0,1,2,3","airportIds":"","ruleDescribeStr":"200.00:20.00","unusableKey":null,"unavailableReason":"","now_time":1504158538},{"is_new":0,"code_str":"049CCB3E","reduce_money":5,"reached_money":50,"isEntire":1,"start_time_str":"2017-08-01","start_time":"1501516800","end_time_str":"2017-08-31","end_time":"1504195199","name":"邀请有礼","description":"满50元减5元","partner_id":"102","unusableKey":null,"unavailableReason":"","now_time":1504158538}],"UNUSABLE":[{"is_new":1,"code_str":"661613992200705","reduce_money":10,"reached_money":1000,"start_time_str":"2017-08-29","start_time":"1503974441","end_time_str":"2017-11-27","end_time":"1511750441","name":"全场通用券","description":"1.适用店铺：免税易购官网、海口机场体验店、三亚机场体验店\r\n2.适用平台：全平台\r\n3.适用商品：所有商品\r\n4.特例商品：无","receiveTag":true,"channelIds":null,"discountDesc":"全场商品适用","isEntire":1,"couponDescribe":null,"goods":[],"conflict":[""],"platformIds":null,"airportIds":null,"ruleDescribeStr":"1000.00:10.00","unusableKey":"NOT_MATCH_RANGE","unavailableReason":"订单没有符合条件的商品","now_time":1504158538}]},"bonus":[{"bonus_id":119970,"money":1,"title":"登录红包","intro":"随便用","effective_date":"2017.08.23至2017.12.30"}]}
     * msg : 获取成功
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
         * coupons : {"USABLE":[{"is_new":0,"code_str":"88285964","reduce_money":10,"reached_money":100,"isEntire":1,"start_time_str":"2017-07-25","start_time":"1500912000","end_time_str":"2017-08-31","end_time":"1504195199","name":"XLP测试","description":"满100元减10元","partner_id":"101","unusableKey":null,"unavailableReason":"","now_time":1504158538,"receiveTag":true,"channelIds":"","discountDesc":"仅可购买兰芝、安娜苏、娇韵诗品牌商品","couponDescribe":null,"goods":[{"barCode":"085715061508","num":10,"price":400}],"conflict":["068372754539013"],"platformIds":"0,1,2,3","airportIds":"","ruleDescribeStr":"200.00:20.00"},{"is_new":1,"code_str":"068372754539013","reduce_money":20,"reached_money":200,"start_time_str":"2017-08-25","start_time":"1503645060","end_time_str":"2017-08-31","end_time":"1504163460","name":"品牌优惠券","description":"1.适用店铺：免税易购官网、海口机场体验店、三亚机场体验店\r\n2.适用平台：全平台\r\n3.适用离岛地点：所有离岛地点\r\n4.适用商品：限兰芝、安娜苏、娇韵诗品牌商品使用\r\n5.特例商品：无","receiveTag":true,"channelIds":"","discountDesc":"仅可购买兰芝、安娜苏、娇韵诗品牌商品","isEntire":1,"couponDescribe":null,"goods":[{"barCode":"085715061508","num":10,"price":400}],"conflict":["068372754539013"],"platformIds":"0,1,2,3","airportIds":"","ruleDescribeStr":"200.00:20.00","unusableKey":null,"unavailableReason":"","now_time":1504158538},{"is_new":0,"code_str":"049CCB3E","reduce_money":5,"reached_money":50,"isEntire":1,"start_time_str":"2017-08-01","start_time":"1501516800","end_time_str":"2017-08-31","end_time":"1504195199","name":"邀请有礼","description":"满50元减5元","partner_id":"102","unusableKey":null,"unavailableReason":"","now_time":1504158538}],"UNUSABLE":[{"is_new":1,"code_str":"661613992200705","reduce_money":10,"reached_money":1000,"start_time_str":"2017-08-29","start_time":"1503974441","end_time_str":"2017-11-27","end_time":"1511750441","name":"全场通用券","description":"1.适用店铺：免税易购官网、海口机场体验店、三亚机场体验店\r\n2.适用平台：全平台\r\n3.适用商品：所有商品\r\n4.特例商品：无","receiveTag":true,"channelIds":null,"discountDesc":"全场商品适用","isEntire":1,"couponDescribe":null,"goods":[],"conflict":[""],"platformIds":null,"airportIds":null,"ruleDescribeStr":"1000.00:10.00","unusableKey":"NOT_MATCH_RANGE","unavailableReason":"订单没有符合条件的商品","now_time":1504158538}]}
         * bonus : [{"bonus_id":119970,"money":1,"title":"登录红包","intro":"随便用","effective_date":"2017.08.23至2017.12.30"}]
         */

        private CouponsBean coupons;
        private List<BonusBean> bonus;

        public CouponsBean getCoupons() {
            return coupons;
        }

        public void setCoupons(CouponsBean coupons) {
            this.coupons = coupons;
        }

        public List<BonusBean> getBonus() {
            return bonus;
        }

        public void setBonus(List<BonusBean> bonus) {
            this.bonus = bonus;
        }

        public static class CouponsBean {
            private List<USABLEBean> USABLE;
            private List<UNUSABLEBean> UNUSABLE;

            public List<USABLEBean> getUSABLE() {
                return USABLE;
            }

            public void setUSABLE(List<USABLEBean> USABLE) {
                this.USABLE = USABLE;
            }

            public List<UNUSABLEBean> getUNUSABLE() {
                return UNUSABLE;
            }

            public void setUNUSABLE(List<UNUSABLEBean> UNUSABLE) {
                this.UNUSABLE = UNUSABLE;
            }

            public static class USABLEBean {
                /**
                 * is_new : 0
                 * code_str : 88285964
                 * reduce_money : 10
                 * reached_money : 100
                 * isEntire : 1
                 * start_time_str : 2017-07-25
                 * start_time : 1500912000
                 * end_time_str : 2017-08-31
                 * end_time : 1504195199
                 * name : XLP测试
                 * description : 满100元减10元
                 * partner_id : 101
                 * unusableKey : null
                 * unavailableReason :
                 * now_time : 1504158538
                 * receiveTag : true
                 * channelIds :
                 * discountDesc : 仅可购买兰芝、安娜苏、娇韵诗品牌商品
                 * couponDescribe : null
                 * goods : [{"barCode":"085715061508","num":10,"price":400}]
                 * conflict : ["068372754539013"]
                 * platformIds : 0,1,2,3
                 * airportIds :
                 * ruleDescribeStr : 200.00:20.00
                 */

                private int is_new;
                private String code_str;
                private double reduce_money;
                private double reached_money;
                private int isEntire;
                private String start_time;
                private String end_time;
                private String name;
                private String discountDesc;
                private List<String> conflict;

                public int getIs_new() {
                    return is_new;
                }

                public void setIs_new(int is_new) {
                    this.is_new = is_new;
                }

                public String getCode_str() {
                    return code_str;
                }

                public void setCode_str(String code_str) {
                    this.code_str = code_str;
                }

                public double getReduce_money() {
                    return reduce_money;
                }

                public void setReduce_money(double reduce_money) {
                    this.reduce_money = reduce_money;
                }

                public double getReached_money() {
                    return reached_money;
                }

                public void setReached_money(double reached_money) {
                    this.reached_money = reached_money;
                }

                public int getIsEntire() {
                    return isEntire;
                }

                public void setIsEntire(int isEntire) {
                    this.isEntire = isEntire;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDiscountDesc() {
                    return discountDesc;
                }

                public void setDiscountDesc(String discountDesc) {
                    this.discountDesc = discountDesc;
                }

                public List<String> getConflict() {
                    return conflict;
                }

                public void setConflict(List<String> conflict) {
                    this.conflict = conflict;
                }
            }

            public static class UNUSABLEBean {
                /**
                 * is_new : 1
                 * code_str : 661613992200705
                 * reduce_money : 10
                 * reached_money : 1000
                 * start_time_str : 2017-08-29
                 * start_time : 1503974441
                 * end_time_str : 2017-11-27
                 * end_time : 1511750441
                 * name : 全场通用券
                 * description : 1.适用店铺：免税易购官网、海口机场体验店、三亚机场体验店
                 2.适用平台：全平台
                 3.适用商品：所有商品
                 4.特例商品：无
                 * receiveTag : true
                 * channelIds : null
                 * discountDesc : 全场商品适用
                 * isEntire : 1
                 * couponDescribe : null
                 * goods : []
                 * conflict : [""]
                 * platformIds : null
                 * airportIds : null
                 * ruleDescribeStr : 1000.00:10.00
                 * unusableKey : NOT_MATCH_RANGE
                 * unavailableReason : 订单没有符合条件的商品
                 * now_time : 1504158538
                 */

                private int is_new;
                private String code_str;
                private double reduce_money;
                private double reached_money;
                private String start_time;
                private String end_time;
                private String name;
                private String discountDesc;
                private int isEntire;
                private String unavailableReason;
                private List<String> conflict;

                public int getIs_new() {
                    return is_new;
                }

                public void setIs_new(int is_new) {
                    this.is_new = is_new;
                }

                public String getCode_str() {
                    return code_str;
                }

                public void setCode_str(String code_str) {
                    this.code_str = code_str;
                }

                public double getReduce_money() {
                    return reduce_money;
                }

                public void setReduce_money(double reduce_money) {
                    this.reduce_money = reduce_money;
                }

                public double getReached_money() {
                    return reached_money;
                }

                public void setReached_money(double reached_money) {
                    this.reached_money = reached_money;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDiscountDesc() {
                    return discountDesc;
                }

                public void setDiscountDesc(String discountDesc) {
                    this.discountDesc = discountDesc;
                }

                public int getIsEntire() {
                    return isEntire;
                }

                public void setIsEntire(int isEntire) {
                    this.isEntire = isEntire;
                }

                public String getUnavailableReason() {
                    return unavailableReason;
                }

                public void setUnavailableReason(String unavailableReason) {
                    this.unavailableReason = unavailableReason;
                }

                public List<String> getConflict() {
                    return conflict;
                }

                public void setConflict(List<String> conflict) {
                    this.conflict = conflict;
                }
            }
        }

        public static class BonusBean {
            /**
             * bonus_id : 119970
             * money : 1
             * title : 登录红包
             * intro : 随便用
             * effective_date : 2017.08.23至2017.12.30
             */

            private String bonus_id;
            private double money;
            private String title;
            private String intro;
            private String effective_date;

            public String getBonus_id() {
                return bonus_id;
            }

            public void setBonus_id(String bonus_id) {
                this.bonus_id = bonus_id;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getEffective_date() {
                return effective_date;
            }

            public void setEffective_date(String effective_date) {
                this.effective_date = effective_date;
            }
        }
    }
}
