package com.hhyg.TyClosing.di.componet;

import com.hhyg.TyClosing.di.module.OrderModule;
import com.hhyg.TyClosing.di.scope.PerActivity;
import com.hhyg.TyClosing.ui.OrderActivity;

import dagger.Component;

/**
 * Created by user on 2017/8/23.
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class,CommonNetParamComponent.class},modules = OrderModule.class)
public interface OrderComponent {
    void inject(OrderActivity activity);
}
