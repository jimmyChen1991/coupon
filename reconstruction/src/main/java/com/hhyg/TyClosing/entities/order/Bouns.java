package com.hhyg.TyClosing.entities.order;

import android.text.SpannableString;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by user on 2017/9/1.
 */

public class Bouns implements MultiItemEntity {

    public final static int BOUNS = 1;

    public final static int DISABLE = 2;
    private int itemType;

    private String bonus_id;
    private double money;
    private String title;
    private String intro;
    private String effective_date;
    private boolean used;
    private SpannableString spannableString;
    private boolean available;

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setSpannableString(SpannableString spannableString) {
        this.spannableString = spannableString;
    }

    public SpannableString getSpannableString() {
        return spannableString;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }

    public Bouns(int itemType) {
        this.itemType = itemType;
    }

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

    @Override
    public int getItemType() {
        return itemType;
    }
}
