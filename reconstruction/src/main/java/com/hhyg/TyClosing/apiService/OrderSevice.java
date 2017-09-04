package com.hhyg.TyClosing.apiService;

import com.hhyg.TyClosing.entities.order.CheckGiftcardRes;
import com.hhyg.TyClosing.entities.order.DiscountRes;
import com.hhyg.TyClosing.entities.order.ExchangecouponRes;
import com.hhyg.TyClosing.entities.order.HasDiscountRes;
import com.hhyg.TyClosing.entities.order.OwnpayRes;
import com.hhyg.TyClosing.entities.order.SearchGiftCardRes;
import com.hhyg.TyClosing.entities.order.SecuryRes;
import com.hhyg.TyClosing.entities.order.SendVaildateCodeRes;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by user on 2017/8/29.
 */

public interface OrderSevice {

    @POST("index.php?r=login/secury")
    @FormUrlEncoded
    Observable<SecuryRes> secury(@Field("parameter") String parameter);

    @POST("index.php?r=login/send")
    @FormUrlEncoded
    Observable<SendVaildateCodeRes> sendVaildateCode (@Field("parameter") String parameter);

    @POST("api/MSService.php")
    @FormUrlEncoded
    Observable<SearchGiftCardRes> searchGiftCard(@Field("parameter") String parameter);

    @POST("api/MSService.php")
    @FormUrlEncoded
    Observable<OwnpayRes> ownpay(@Field("parameter") String parameter);

    @POST("index.php?r=couponsnew/getdiscount")
    @FormUrlEncoded
    Observable<DiscountRes> getDiscount(@Field("parameter") String parameter);

    @POST("index.php?r=couponsnew/hascouponsorcash")
    @FormUrlEncoded
    Observable<HasDiscountRes> checkAvailable(@Field("parameter") String parameter);

    @POST("index.php?r=giftcard/checkin")
    @FormUrlEncoded
    Observable<CheckGiftcardRes> setCardStatus(@Field("parameter") String parameter);

    @POST("index.php?r=couponsnew/exchangecoupons")
    @FormUrlEncoded
    Observable<ExchangecouponRes> exchangeCoupon(@Field("parameter") String parameter);

}
