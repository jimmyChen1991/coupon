package com.hhyg.TyClosing.di.module;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.hhyg.TyClosing.apiService.OrderSevice;
import com.hhyg.TyClosing.entities.CommonParam;
import com.hhyg.TyClosing.info.GoodSku;
import com.hhyg.TyClosing.ui.fragment.order.BounsFragment;
import com.hhyg.TyClosing.ui.fragment.order.CouponFragment;
import com.hhyg.TyClosing.ui.fragment.order.GiftcardFragment;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by user on 2017/8/23.
 */
@Module
public class OrderModule {
    private String info;

    public OrderModule(String info) {
        this.info = info;
    }

    @Provides
    Gson ProvideGson(){
        return new Gson();
    }

    @Provides
    List<GoodSku> provideJsonarray(){
        JSONObject jsonObject = null;
        jsonObject = JSONObject.parseObject(info);
        List<GoodSku> dataList = JSONObject.parseArray(jsonObject.getJSONArray("goodsSku").toString(), GoodSku.class);
        return dataList;
    }

    @Provides
    @Named("slowIndex")
    OrderSevice provideSevice(@Named("slowIndexApi") Retrofit retrofit){
        return retrofit.create(OrderSevice.class);
    }

    @Provides
    @Named("fastIndex")
    OrderSevice provideSevice2(@Named("fastIndexApi") Retrofit retrofit){
        return retrofit.create(OrderSevice.class);
    }

    @Provides
    @Named("slowMsService")
    OrderSevice provideSevice3(@Named("slowServiceApi") Retrofit retrofit){
        return retrofit.create(OrderSevice.class);
    }

    @Provides
    @Named("fastMsService")
    OrderSevice provideSevice4(@Named("fastServiceApi") Retrofit retrofit){
        return retrofit.create(OrderSevice.class);
    }

    @Provides
    CompositeDisposable provideDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    GiftcardFragment provideGiftcardFragment(@Named("fastIndex") OrderSevice indexSevice,@Named("fastMsService") OrderSevice msSevice,CommonParam param){
        GiftcardFragment giftcardFragment = new GiftcardFragment();
        giftcardFragment.setIndexSevice(indexSevice);
        giftcardFragment.setMsSevice(msSevice);
        giftcardFragment.setCommonParam(param);
        return giftcardFragment;
    }

    @Provides
    CouponFragment provideCouponFragment(@Named("fastIndex") OrderSevice indexSevice,CommonParam param){
        CouponFragment couponFragment = new CouponFragment();
        couponFragment.setIndexSevice(indexSevice);
        couponFragment.setCommonParam(param);
        return couponFragment;
    }

    @Provides
    BounsFragment provideBounsFragment(){
        BounsFragment bounsFragment = new BounsFragment();
        return bounsFragment;
    }
}
