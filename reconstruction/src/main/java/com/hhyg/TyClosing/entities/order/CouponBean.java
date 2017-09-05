package com.hhyg.TyClosing.entities.order;

import java.util.List;

/**
 * Created by chenqiyang on 2017/9/3.
 */

public class CouponBean {
    private int is_new;
    private String code_str;
    private double reduce_money;
    private double reached_money;
    private int isEntire;
    private String start_time;
    private String end_time;
    private String name;
    private String discountDesc;
    private String unavailableReason;
    private List<String> conflict;

    public void setUnavailableReason(String unavailableReason) {
        this.unavailableReason = unavailableReason;
    }

    public String getUnavailableReason() {
        return unavailableReason;
    }

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
