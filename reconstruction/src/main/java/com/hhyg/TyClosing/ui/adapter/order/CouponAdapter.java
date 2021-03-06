package com.hhyg.TyClosing.ui.adapter.order;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.entities.order.Coupon;

import java.util.List;

/**
 * Created by user on 2017/9/3.
 */

public class CouponAdapter extends BaseMultiItemQuickAdapter<Coupon,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CouponAdapter(List<Coupon> data) {
        super(data);
        addItemType(Coupon.TITLE, R.layout.coupon_title);
        addItemType(Coupon.COUPON,R.layout.adapter_coupon);
        addItemType(Coupon.DISABLE,R.layout.adapter_disable);

    }

    @Override
    protected void convert(BaseViewHolder helper, Coupon item) {
        switch (helper.getItemViewType()){
            case Coupon.TITLE:
                TextView tv = helper.getView(R.id.title) ;
                if(item.isEnable()){
                    helper.setText(R.id.title,"此单可用 （" + item.getCount() + "）");
                    tv.setTextColor(Color.parseColor("#C38C56"));
                }else{
                    helper.setText(R.id.title,"此单不可用 （" + item.getCount() + "）");
                    tv.setTextColor(Color.parseColor("#333333"));
                }
                break;
            case Coupon.DISABLE:
                helper.setText(R.id.title,"不使用优惠券");
                if(item.isDiableUsed()){
                    helper.getView(R.id.right_icon).setBackgroundResource(R.drawable.coupon_checked);
                }else{
                    helper.getView(R.id.right_icon).setBackgroundResource(R.drawable.disable);
                }
                break;
            case Coupon.COUPON:
                helper.setText(R.id.count,item.getSpannableString())
                        .setText(R.id.reached,"满" + (int)item.getReached_money() + "可用")
                        .setText(R.id.name,item.getNameTv());
                if(item.isEnable() && item.isUsed()){
                    helper.getView(R.id.wrap).setBackgroundResource(R.drawable.shape_coupon);
                    helper.getView(R.id.countWrap).setBackgroundResource(R.color.coupon);
                    helper.getView(R.id.right_icon).setBackgroundResource(R.drawable.coupon_checked);
                }else{
                    helper.getView(R.id.wrap).setBackgroundResource(R.drawable.shape_disable_ordercut);
                    helper.getView(R.id.countWrap).setBackgroundResource(R.color.price);
                    helper.getView(R.id.right_icon).setBackgroundResource(R.drawable.disable_ordercut);
                }

                if(item.isEnable()){
                    if(!item.isAvailable()){
                        helper.setText(R.id.bottom_tv,item.getUnavailableReason());
                    }else if(!item.isPriceAvailable()){
                        helper.setText(R.id.bottom_tv,item.getUnavailableReason());
                    }else{
                        helper.setText(R.id.bottom_tv,item.getTimeTv());
                    }
                }else{
                    helper.setText(R.id.bottom_tv,item.getUnavailableReason());
                }
                break;

        }
    }
}
