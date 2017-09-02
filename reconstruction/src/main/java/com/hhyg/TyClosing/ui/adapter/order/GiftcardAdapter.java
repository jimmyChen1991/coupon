package com.hhyg.TyClosing.ui.adapter.order;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.entities.order.Giftcard;

import java.util.List;

/**
 * Created by user on 2017/9/1.
 */

public class GiftcardAdapter extends BaseMultiItemQuickAdapter<Giftcard,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public GiftcardAdapter(Context context,List<Giftcard> data) {
        super(data);
        addItemType(Giftcard.CARD, R.layout.adapter_giftcard);
        addItemType(Giftcard.DISABLE,R.layout.adapter_disable);
    }

    @Override
    protected void convert(BaseViewHolder helper, Giftcard item) {
        switch (helper.getItemViewType()){
            case Giftcard.CARD:
                helper.setText(R.id.cardId,item.getBarcode())
                        .setText(R.id.bottom_tv,item.getBottemContent())
                        .setText(R.id.count,item.getSpannableString());
                break;
            case Giftcard.DISABLE:
                helper.setText(R.id.title,"不使用礼品卡");
                break;
        }

    }
}