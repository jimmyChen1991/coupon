package com.hhyg.TyClosing.apiService;

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


}
