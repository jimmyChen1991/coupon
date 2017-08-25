package com.hhyg.TyClosing.di.module;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 2017/8/23.
 */
@Module
public class OrderModule {

    @Provides
    Gson ProvideGson(){
        return new Gson();
    }
}
