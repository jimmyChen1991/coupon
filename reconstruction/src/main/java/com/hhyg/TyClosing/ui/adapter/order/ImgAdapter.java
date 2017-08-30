package com.hhyg.TyClosing.ui.adapter.order;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.entities.order.VaildateInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by user on 2017/8/28.
 */

public class ImgAdapter extends BaseQuickAdapter<VaildateInfo.GoodsSkuBean,BaseViewHolder>{

    public ImgAdapter(@LayoutRes int layoutResId, @Nullable List<VaildateInfo.GoodsSkuBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VaildateInfo.GoodsSkuBean item) {
        helper.setVisible(R.id.youshui,!TextUtils.isEmpty(item.getTax_display_txt()));
        if(!TextUtils.isEmpty(item.getGoods_img())){
            Picasso.with(helper.itemView.getContext()).load(item.getGoods_img()).into((ImageView) helper.getView(R.id.img));
        }
    }
}
