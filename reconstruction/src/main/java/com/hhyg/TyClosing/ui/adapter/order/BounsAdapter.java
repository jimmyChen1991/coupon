package com.hhyg.TyClosing.ui.adapter.order;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.entities.order.Bouns;

import java.util.List;

/**
 * Created by user on 2017/9/3.
 */

public class BounsAdapter extends BaseMultiItemQuickAdapter<Bouns,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BounsAdapter(List<Bouns> data) {
        super(data);
        addItemType(Bouns.BOUNS, R.layout.adapter_bouns);
        addItemType(Bouns.DISABLE,R.layout.adapter_disable);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bouns item) {
        switch (helper.getItemViewType()){
            case Bouns.BOUNS:
                helper.setText(R.id.name,item.getTitle())
                        .setText(R.id.count,item.getSpannableString())
                        .setText(R.id.bottom_tv,item.getEffective_date());
                if(item.isUsed()){
                    helper.getView(R.id.wrap).setBackgroundResource(R.drawable.shape_bouns);
                    helper.getView(R.id.countWrap).setBackgroundResource(R.color.bouns);
                    helper.getView(R.id.right_icon).setBackgroundResource(R.drawable.bouns_checked);
                }else{
                    helper.getView(R.id.wrap).setBackgroundResource(R.drawable.shape_disable_alldiscount);
                    helper.getView(R.id.countWrap).setBackgroundResource(R.color.price);
                    helper.getView(R.id.right_icon).setBackgroundResource(R.drawable.disable_ordercut);
                }

                break;
            case Bouns.DISABLE:
                helper.setText(R.id.title,"不使用红包");
                break;

        }
    }
}
