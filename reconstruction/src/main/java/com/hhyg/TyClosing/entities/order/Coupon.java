package com.hhyg.TyClosing.entities.order;

import android.text.SpannableString;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by user on 2017/9/1.
 */

public class Coupon implements MultiItemEntity{

    public final static int TITLE = 1;

    public final static int COUPON = 2;

    public final static int DISABLE = 3;

    private int itemType;
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
    private String timeTv;
    private SpannableString spannableString;
    private boolean used;
    private boolean enable;
    private int count;
    private String nameTv;

    public void setNameTv(String nameTv) {
        this.nameTv = nameTv;
    }

    public String getNameTv() {
        return nameTv;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public Coupon(int itemType) {
        this.itemType = itemType;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }

    public void setSpannableString(SpannableString spannableString) {
        this.spannableString = spannableString;
    }

    public SpannableString getSpannableString() {
        return spannableString;
    }

    public void setTimeTv(String timeTv) {
        this.timeTv = timeTv;
    }

    public String getTimeTv() {
        return timeTv;
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

    @Override
    public int getItemType() {
        return itemType;
    }
}
