package com.hhyg.TyClosing.di.module;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.hhyg.TyClosing.apiService.OrderSevice;
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
    OrderSevice provideSevice(@Named("slowIndexApi") Retrofit retrofit){
        return retrofit.create(OrderSevice.class);
    }

    @Provides
    CompositeDisposable provideDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    CouponFragment provideCouponFragment(){
        return new CouponFragment();
    }

    @Provides
    BounsFragment provideBounsFragment(){
        return new BounsFragment();
    }

    @Provides
    GiftcardFragment provideGiftFragment(){
        return new GiftcardFragment();
    }
}
