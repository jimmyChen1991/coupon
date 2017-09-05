package com.hhyg.TyClosing.entities.order;

import android.text.SpannableString;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by user on 2017/9/1.
 */

public class Giftcard implements MultiItemEntity {

    public static final int CARD = 1;

    public static final int DISABLE = 2;

    private String barcode;
    private String temp_order_key;
    private double money;
    private String time_begin;
    private String time_end;
    private String detail;
    private int itemType;
    private String BottemContent;
    private SpannableString spannableString;
    private String timeTv;
    private boolean used;
    private boolean available;
    private String unavailableReason;

    public void setUnavailableReason(String unavailableReason) {
        this.unavailableReason = unavailableReason;
    }

    public String getUnavailableReason() {
        return unavailableReason;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setTimeTv(String timeTv) {
        this.timeTv = timeTv;
    }

    public String getTimeTv() {
        return timeTv;
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

    public Giftcard(int itemType) {
        this.itemType = itemType;
    }

    public void setBottemContent(String bottemContent) {
        BottemContent = bottemContent;
    }

    public String getBottemContent() {
        return BottemContent;
    }

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

    @Override
    public int getItemType() {
        return itemType;
    }
}
