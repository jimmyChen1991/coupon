package com.hhyg.TyClosing.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.apiService.OrderSevice;
import com.hhyg.TyClosing.config.Constants;
import com.hhyg.TyClosing.di.componet.DaggerCommonNetParamComponent;
import com.hhyg.TyClosing.di.componet.DaggerOrderComponent;
import com.hhyg.TyClosing.di.module.CommonNetParamModule;
import com.hhyg.TyClosing.di.module.OrderModule;
import com.hhyg.TyClosing.entities.CommonParam;
import com.hhyg.TyClosing.entities.order.Bouns;
import com.hhyg.TyClosing.entities.order.Coupon;
import com.hhyg.TyClosing.entities.order.CouponBean;
import com.hhyg.TyClosing.entities.order.DiscountReq;
import com.hhyg.TyClosing.entities.order.DiscountRes;
import com.hhyg.TyClosing.entities.order.Giftcard;
import com.hhyg.TyClosing.entities.order.HasDiscountReq;
import com.hhyg.TyClosing.entities.order.HasDiscountRes;
import com.hhyg.TyClosing.entities.order.OwnpayReq;
import com.hhyg.TyClosing.entities.order.OwnpayRes;
import com.hhyg.TyClosing.entities.order.SecuryReq;
import com.hhyg.TyClosing.entities.order.SecuryRes;
import com.hhyg.TyClosing.entities.order.SendVaildateCodeReq;
import com.hhyg.TyClosing.entities.order.SendVaildateCodeRes;
import com.hhyg.TyClosing.entities.order.VaildateInfo;
import com.hhyg.TyClosing.exceptions.ServiceMsgException;
import com.hhyg.TyClosing.global.MyApplication;
import com.hhyg.TyClosing.info.GoodSku;
import com.hhyg.TyClosing.mgr.ClosingRefInfoMgr;
import com.hhyg.TyClosing.mgr.OrderPrice;
import com.hhyg.TyClosing.mgr.ShoppingCartMgr;
import com.hhyg.TyClosing.ui.dialog.CustomAlertDialog;
import com.hhyg.TyClosing.ui.dialog.CustomConfirmDialog;
import com.hhyg.TyClosing.ui.dialog.OrderConfirmDialog;
import com.hhyg.TyClosing.ui.fragment.AutoSettleOrderItemsFragment;
import com.hhyg.TyClosing.ui.fragment.order.BounsFragment;
import com.hhyg.TyClosing.ui.fragment.order.CouponFragment;
import com.hhyg.TyClosing.ui.fragment.order.GiftcardFragment;
import com.hhyg.TyClosing.util.CouponUtil;
import com.hhyg.TyClosing.util.ProgressDialogUtil;
import com.hhyg.TyClosing.util.SpannableUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OrderActivity extends AppCompatActivity implements OrderPrice,CouponFragment.CouponOp,BounsFragment.BounsOp,GiftcardFragment.GiftcardOp {

    private static final String TAG = "OrderActivity";

    @Inject
    Gson gson;
    @Inject
    List<GoodSku> goodData;
    VaildateInfo vaildateInfo;
    @Inject
    CommonParam commonParam;
    @Inject
    @Named("slowIndex")
    OrderSevice slowIndexOrderSevice;
    @Inject
    @Named("fastIndex")
    OrderSevice fastIndexOrderSevice;
    @Inject
    @Named("slowMsService")
    OrderSevice slowMsOrderSevice;
    @Inject
    @Named("fastMsService")
    OrderSevice fastMsOrderSevice;
    @Inject
    CompositeDisposable disposables;
    @Inject
    CouponFragment couponFragment;
    @Inject
    BounsFragment bounsFragment;
    @Inject
    GiftcardFragment giftcardFragment;
    MyTimer mTimer = new MyTimer(60000, 1000);
    @BindView(R.id.contentWrap)
    View contentWrap;
    @BindView(R.id.user_infoleft)
    TextView userInfoleft;
    @BindView(R.id.user_inforight)
    TextView userInforight;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.coupon_warnning)
    TextView couponWarnning;
    @BindView(R.id.bouns_warnning)
    TextView bounsWarnning;
    @BindView(R.id.giftcard_warnning)
    TextView giftcardWarnning;
    @BindView(R.id.giftcard_entry)
    View giftEntry;
    @BindView(R.id.giftcard_split)
    View getGiftSplit;
    @BindView(R.id.textview_info1)
    TextView goodsPrice;
    @BindView(R.id.textview_info3)
    TextView discount;
    @BindView(R.id.textview_info4)
    TextView fullActive;
    @BindView(R.id.textview_info41)
    TextView everyCountActive;
    @BindView(R.id.textview_info42)
    TextView everyFullActive;
    @BindView(R.id.textview_info5)
    TextView counpon;
    @BindView(R.id.textview_info6)
    TextView giftCard;
    @BindView(R.id.textview_info61)
    TextView hongbao;
    @BindView(R.id.textview_info7)
    TextView tax;
    @BindView(R.id.textview_info8)
    TextView finalPrice;
    @BindView(R.id.bottomTitle)
    TextView bottomPrice;
    @BindView(R.id.vipWrap)
    View vipView;
    @BindView(R.id.memeberaccount)
    TextView vipName;
    @BindView(R.id.button_get_code)
    Button getVaildateCode;
    @BindView(R.id.button_code_check)
    Button vaildateCode;
    @BindView(R.id.member_input_code)
    EditText vaildateCodeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        DaggerOrderComponent.builder()
                .applicationComponent(MyApplication.GetInstance().getComponent())
                .commonNetParamComponent(DaggerCommonNetParamComponent.builder().commonNetParamModule(new CommonNetParamModule()).build())
                .orderModule(new OrderModule(getIntent().getStringExtra("data")))
                .build()
                .inject(this);

        vaildateInfo = gson.fromJson(getIntent().getStringExtra("data"), VaildateInfo.class);
        setMoney();
        initDiscountFragment();
        Observable.just(commonParam)
                .map(new Function<CommonParam, HasDiscountReq>() {
                    @Override
                    public HasDiscountReq apply(@NonNull CommonParam param) throws Exception {
                        HasDiscountReq req = new HasDiscountReq();
                        HasDiscountReq.DataBean data = new HasDiscountReq.DataBean();
                        data.setMobile_phone(vaildateInfo.getPhone());
                        req.setData(data);
                        req.setChannel(param.getChannelId());
                        req.setImei(param.getImei());
                        req.setShopid(param.getShopId());
                        return req;
                    }
                })
                .flatMap(new Function<HasDiscountReq, ObservableSource<HasDiscountRes>>() {
                    @Override
                    public ObservableSource<HasDiscountRes> apply(@NonNull HasDiscountReq hasDiscountReq) throws Exception {
                        return fastIndexOrderSevice.checkAvailable(gson.toJson(hasDiscountReq));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        contentWrap.setVisibility(View.VISIBLE);
                        initUserInfo();
                        setGoodsImages();
                        AutoSettleOrderItemsFragment fragment =
                                (AutoSettleOrderItemsFragment) (getFragmentManager().findFragmentById(R.id.orderDetailFragment));
                        fragment.setData(goodData);
                    }
                })
                .subscribe(new Observer<HasDiscountRes>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@NonNull HasDiscountRes hasDiscountRes) {
                        if (hasDiscountRes.getErrcode() == 1) {
                            vaildateInfo.setAvailable(1);
                            vipView.setVisibility(View.VISIBLE);
                            vipName.setText("账户 ：" + vaildateInfo.getPhone());
                        } else {
                            vipView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, e.toString());
                        vipView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        if (ClosingRefInfoMgr.getInstance().getLoginConfig().isCard_active()) {
            giftEntry.setVisibility(View.VISIBLE);
        } else {
            getGiftSplit.setVisibility(View.GONE);
            giftEntry.setVisibility(View.GONE);
        }
    }

    private void initDiscountFragment() {
        couponFragment.setCouponOp(this);
        couponFragment.setOrderPrice(this);
        giftcardFragment.setGiftcardListener(this);
        giftcardFragment.setOrderPrice(this);
        bounsFragment.setBounsOp(this);
        bounsFragment.setOrderPrice(this);
    }

    private void initUserInfo() {
        final VaildateInfo.UserInfoBean userInfo = vaildateInfo.getUserInfo();
        StringBuilder sb1 = new StringBuilder();
        sb1.append("离岛人姓名：")
                .append(userInfo.getUserName())
                .append("\n")
                .append("手机号码：")
                .append(userInfo.getPhone())
                .append("\n")
                .append("离岛时间：")
                .append(userInfo.getFlightDate())
                .append("\n")
                .append("提货时间：")
                .append(vaildateInfo.getDeliverTime());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("离岛人身份证：")
                .append(userInfo.getIdCard())
                .append("\n")
                .append("离岛航班：")
                .append(userInfo.getFlightNum())
                .append("\n")
                .append("离岛机场：")
                .append(MyApplication.GetInstance().getUserSelectAir().name)
                .append("\n")
                .append("销售员：")
                .append(ClosingRefInfoMgr.getInstance().getSalerName() + " " + ClosingRefInfoMgr.getInstance().getUserName());
        userInfoleft.setText(sb1);
        userInforight.setText(sb2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
        mTimer.cancel();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @OnClick(R.id.imgWrap)
    public void onViewClicked2() {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    @OnClick({R.id.coupon_entry, R.id.bouns_entry, R.id.giftcard_entry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.coupon_entry:
                couponFragment.show(getFragmentManager(), "coupon");
                break;
            case R.id.bouns_entry:
                bounsFragment.show(getFragmentManager(), "bouns");
                break;
            case R.id.giftcard_entry:
                giftcardFragment.show(getFragmentManager(), "giftcard");
                break;
        }
    }

    @OnClick(R.id.button_get_code)
    public void onViewClicked3(final View view) {
        view.setEnabled(false);
        getVaildateCode.setClickable(false);
        Observable.just(commonParam)
                .map(new Function<CommonParam, SecuryReq>() {
                    @Override
                    public SecuryReq apply(@NonNull CommonParam param) throws Exception {
                        SecuryReq req = new SecuryReq(param);
                        SecuryReq.DataBean data = new SecuryReq.DataBean();
                        data.setMobilephone(vaildateInfo.getPhone());
                        req.setData(data);
                        return req;
                    }
                })
                .flatMap(new Function<SecuryReq, ObservableSource<SecuryRes>>() {
                    @Override
                    public ObservableSource<SecuryRes> apply(@NonNull SecuryReq securyReq) throws Exception {
                        return slowIndexOrderSevice.secury(gson.toJson(securyReq));
                    }
                })
                .map(new Function<SecuryRes, SendVaildateCodeReq>() {
                    @Override
                    public SendVaildateCodeReq apply(@NonNull SecuryRes securyRes) throws Exception {
                        SendVaildateCodeReq req = new SendVaildateCodeReq(commonParam);
                        SendVaildateCodeReq.DataBean data = new SendVaildateCodeReq.DataBean();
                        data.setMobilephone(vaildateInfo.getPhone());
                        data.setSign(securyRes.getData().getSign());
                        data.setTimestamp(securyRes.getData().getTimestamp());
                        req.setData(data);
                        return req;
                    }
                })
                .flatMap(new Function<SendVaildateCodeReq, ObservableSource<SendVaildateCodeRes>>() {
                    @Override
                    public ObservableSource<SendVaildateCodeRes> apply(@NonNull SendVaildateCodeReq sendVaildateCodeReq) throws Exception {
                        return slowIndexOrderSevice.sendVaildateCode(gson.toJson(sendVaildateCodeReq));
                    }
                })
                .doOnNext(new Consumer<SendVaildateCodeRes>() {
                    @Override
                    public void accept(@NonNull SendVaildateCodeRes sendVaildateCodeRes) throws Exception {
                        if (sendVaildateCodeRes.getErrcode() != 1) {
                            throw new ServiceMsgException(sendVaildateCodeRes.getMsg());
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        ProgressDialogUtil.hide();
                    }
                })
                .subscribe(new Observer<SendVaildateCodeRes>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        ProgressDialogUtil.show(OrderActivity.this);
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@NonNull SendVaildateCodeRes sendVaildateCodeRes) {
                        Toasty.success(OrderActivity.this, sendVaildateCodeRes.getMsg(), Toast.LENGTH_SHORT).show();
                        mTimer.start();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e instanceof ServiceMsgException) {
                            Toasty.error(OrderActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toasty.error(OrderActivity.this, getString(R.string.netconnect_exception), Toast.LENGTH_SHORT).show();
                        }
                        getVaildateCode.setClickable(true);
                        getVaildateCode.setEnabled(true);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick(R.id.button_code_check)
    public void onViewClicked4() {
        final String input = vaildateCodeInput.getText().toString();
        if (TextUtils.isEmpty(input)) {
            Toasty.warning(this, "请输入验证码", Toast.LENGTH_SHORT).show();
        } else {
            Observable.just(commonParam)
                    .map(new Function<CommonParam, DiscountReq>() {
                        @Override
                        public DiscountReq apply(@NonNull CommonParam param) throws Exception {
                            return getDiscountReq(input,true);
                        }
                    })
                    .flatMap(new Function<DiscountReq, ObservableSource<DiscountRes>>() {
                        @Override
                        public ObservableSource<DiscountRes> apply(@NonNull DiscountReq discountReq) throws Exception {
                            return fastIndexOrderSevice.getDiscount(gson.toJson(discountReq));
                        }
                    })
                    .doOnNext(new Consumer<DiscountRes>() {
                        @Override
                        public void accept(@NonNull DiscountRes discountRes) throws Exception {
                            if (discountRes.getErrcode() != 1) {
                                throw new ServiceMsgException(discountRes.getMsg());
                            }
                        }
                    })
                    .doOnNext(new Consumer<DiscountRes>() {
                        @Override
                        public void accept(@NonNull DiscountRes discountRes) throws Exception {
                            if (discountRes.getData().getBonus() != null && discountRes.getData().getBonus().size() != 0) {
                                double thePrice = getFianlPrice();
                                for (DiscountRes.DataBean.BonusBean bean : discountRes.getData().getBonus()) {
                                    Bouns bouns = new Bouns(Bouns.BOUNS);
                                    bouns.setSpannableString(SpannableUtil.discountSpan(bean.getMoney()));
                                    bouns.setMoney(bean.getMoney());
                                    bouns.setBonus_id(bean.getBonus_id());
                                    bouns.setEffective_date(bean.getEffective_date());
                                    bouns.setTitle(bean.getTitle());
                                    bouns.setIntro(bean.getIntro());
                                    bouns.setUnavailableReason("面额大于实付金额");
                                    if(thePrice <= bouns.getMoney()){
                                        bouns.setAvailable(false);
                                    }else {
                                        bouns.setAvailable(true);
                                    }
                                    bounsFragment.addBouns(bouns);
                                }

                            }
                        }
                    })
                    .doOnNext(new Consumer<DiscountRes>() {
                        @Override
                        public void accept(@NonNull DiscountRes discountRes) throws Exception {
                            final DiscountRes.DataBean.CouponsBean couponsBean = discountRes.getData().getCoupons();
                            if (couponsBean != null && couponsBean.getUSABLE() != null && couponsBean.getUSABLE().size() != 0) {
                                Coupon title = new Coupon(Coupon.TITLE);
                                title.setEnable(true);
                                title.setCount(couponsBean.getUSABLE().size());
                                couponFragment.addCoupon(title);
                                double thePrice = getFianlPrice();
                                for (int index = 0; index < couponsBean.getUSABLE().size(); index++) {
                                    CouponBean res = couponsBean.getUSABLE().get(index);
                                    Coupon coupon = new Coupon(Coupon.COUPON);
                                    if(thePrice <= coupon.getReduce_money()){
                                        coupon.setAvailable(false);
                                        coupon.setUnavailableReason("面额大于实付金额");
                                    }else {
                                        coupon.setAvailable(true);
                                    }
                                    coupon.setEnable(true);
                                    CouponUtil.initCoupon(res, coupon);
                                    couponFragment.addCoupon(coupon);
                                }
                                Coupon disable = new Coupon(Coupon.DISABLE);
                                disable.setEnable(true);
                                couponFragment.addCoupon(disable);
                            }
                        }
                    })
                    .doOnNext(new Consumer<DiscountRes>() {
                        @Override
                        public void accept(@NonNull DiscountRes discountRes) throws Exception {
                            final DiscountRes.DataBean.CouponsBean couponsBean = discountRes.getData().getCoupons();
                            if (couponsBean != null && couponsBean.getUNUSABLE() != null && couponsBean.getUNUSABLE().size() != 0) {
                                Coupon title = new Coupon(Coupon.TITLE);
                                title.setEnable(false);
                                title.setCount(couponsBean.getUNUSABLE().size());
                                couponFragment.addCoupon(title);
                                for (int index = 0; index < couponsBean.getUNUSABLE().size(); index++) {
                                    CouponBean res = couponsBean.getUNUSABLE().get(index);
                                    Coupon coupon = new Coupon(Coupon.COUPON);
                                    coupon.setEnable(false);
                                    CouponUtil.initCoupon(res,coupon);
                                    couponFragment.addCoupon(coupon);
                                }
                            }
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(new Action() {
                        @Override
                        public void run() throws Exception {
                            ProgressDialogUtil.hide();
                        }
                    })
                    .subscribe(new Observer<DiscountRes>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            disposables.add(d);
                            ProgressDialogUtil.show(OrderActivity.this);
                        }

                        @Override
                        public void onNext(@NonNull DiscountRes discountRes) {
                            Toasty.success(OrderActivity.this, discountRes.getMsg(), Toast.LENGTH_SHORT).show();
                            showSuccessUI();
                            freshSelectedText();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            if (e instanceof ServiceMsgException) {
                                Toasty.error(OrderActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toasty.error(OrderActivity.this, getString(R.string.netconnect_exception), Toast.LENGTH_SHORT).show();
                            }
                            Log.d(TAG, e.toString());

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }

    }

    private DiscountReq getDiscountReq(String input,boolean needCheckCode) {
        DiscountReq req = new DiscountReq();
        DiscountReq.DataBean data = new DiscountReq.DataBean();
        req.setChannel(commonParam.getChannelId());
        req.setImei(commonParam.getImei());
        req.setPlatformId(commonParam.getPlatformId());
        req.setShopid(commonParam.getShopId());
        data.setNeedcheckcode(needCheckCode ? 1 : 0);
//        data.setNeedcheckcode(0);
        data.setCode(input);
        data.setFinal_total_price(vaildateInfo.getFinalPrice());
        data.setDeliverplace(String.valueOf(ClosingRefInfoMgr.getInstance().getCurPickupId()));
        data.setMobile_phone(vaildateInfo.getPhone());
        data.setToken(vaildateInfo.getToken());
        List<DiscountReq.DataBean.GoodslistBean> goods = new ArrayList<DiscountReq.DataBean.GoodslistBean>();
        for (VaildateInfo.GoodsSkuBean bean : vaildateInfo.getGoodsSku()) {
            DiscountReq.DataBean.GoodslistBean res = new DiscountReq.DataBean.GoodslistBean();
            res.setBarcode(bean.getBarcode());
            res.setNum(bean.getNumber());
            goods.add(res);
        }
        data.setGoodslist(goods);
        req.setData(data);
        return req;
    }

    @OnClick(R.id.button_goto_pay)
    public void onViewClicked5() {
        OrderConfirmDialog customConfirmDialog = new OrderConfirmDialog();
        customConfirmDialog.setMsgInfo(gson.toJson(vaildateInfo.getUserInfo()));
        customConfirmDialog.setConfirmBtnText(getString(R.string.goback_to_shopcart));
        customConfirmDialog.setTime(vaildateInfo.getDeliverTime());
        customConfirmDialog.setCancelBtnText(getString(R.string.queding));
        customConfirmDialog.setAction(new CustomConfirmDialog.Action() {
            @Override
            public void process() {
                ownpay();
            }

            @Override
            public void cancel() {
            }

            @Override
            public void close() {
            }
        });
        customConfirmDialog.show(getFragmentManager(), "customConfirmDialog");

    }

    private void ownpay() {
        Observable.just(vaildateInfo.getGoodsSku())
                .map(new Function<List<VaildateInfo.GoodsSkuBean>, OwnpayReq.DataBean>() {
                    @Override
                    public OwnpayReq.DataBean apply(@NonNull List<VaildateInfo.GoodsSkuBean> goodsSkuBeen) throws Exception {
                        OwnpayReq.DataBean data = new OwnpayReq.DataBean();
                        ArrayList<OwnpayReq.DataBean.GoodsSkuBean> res = new ArrayList<>();
                        for (VaildateInfo.GoodsSkuBean bean : goodsSkuBeen) {
                            OwnpayReq.DataBean.GoodsSkuBean sku = new OwnpayReq.DataBean.GoodsSkuBean();
                            sku.setActivity(bean.getActivity());
                            sku.setBarcode(bean.getBarcode());
                            sku.setNumber(bean.getNumber());
                            res.add(sku);
                        }
                        data.setGoodsSku(res);
                        return data;
                    }
                })
                .doOnNext(new Consumer<OwnpayReq.DataBean>() {
                    @Override
                    public void accept(@NonNull OwnpayReq.DataBean dataBean) throws Exception {
                        dataBean.setFlightDate(vaildateInfo.getUserInfo().getFlightDate());
                        dataBean.setFlightNum(vaildateInfo.getUserInfo().getFlightNum());
                        dataBean.setDeliverPlace(ClosingRefInfoMgr.getInstance().getCurPickupId());
                        dataBean.setDeliverTime(vaildateInfo.getDeliverTime());
                        dataBean.setIdCard(vaildateInfo.getUserInfo().getIdCard());
                        dataBean.setPhone(vaildateInfo.getUserInfo().getPhone());
                        dataBean.setUserName(vaildateInfo.getUserInfo().getUserName());
                        dataBean.setSubmitTime(new Date().getTime());
                        dataBean.setToken(vaildateInfo.getToken());
                    }
                })
                .doOnNext(new Consumer<OwnpayReq.DataBean>() {
                    @Override
                    public void accept(@NonNull OwnpayReq.DataBean dataBean) throws Exception {
                        StringBuilder sb = new StringBuilder();
                        for (Coupon coupon : couponFragment.getCoupons()){
                            if(coupon.isEnable() && coupon.isUsed()){
                                sb.append(coupon.getCode_str());
                                sb.append(",");
                            }
                        }
                        dataBean.setCouponCode(sb.toString());
                    }
                })
                .doOnNext(new Consumer<OwnpayReq.DataBean>() {
                    @Override
                    public void accept(@NonNull OwnpayReq.DataBean dataBean) throws Exception {
                        dataBean.setGiftcardKey(giftcardFragment.getToken());
                    }
                })
                .map(new Function<OwnpayReq.DataBean, OwnpayReq>() {
                    @Override
                    public OwnpayReq apply(@NonNull OwnpayReq.DataBean dataBean) throws Exception {
                        OwnpayReq req = new OwnpayReq();
                        req.setImei(commonParam.getImei());
                        req.setShopid(commonParam.getShopId());
                        req.setChannel(commonParam.getChannelId());
                        req.setPlatformId(commonParam.getPlatformId());
                        req.setSaleId(ClosingRefInfoMgr.getInstance().getSalerId());
                        req.setData(dataBean);
                        return req;
                    }
                })
                .flatMap(new Function<OwnpayReq, ObservableSource<OwnpayRes>>() {
                    @Override
                    public ObservableSource<OwnpayRes> apply(@NonNull OwnpayReq ownpayReq) throws Exception {
                        return slowMsOrderSevice.ownpay(gson.toJson(ownpayReq));
                    }
                })
                .doOnNext(new Consumer<OwnpayRes>() {
                    @Override
                    public void accept(@NonNull OwnpayRes ownpayRes) throws Exception {
                        if (ownpayRes.getErrcode() != 1) {
                            throw new ServiceMsgException(ownpayRes.getMsg());
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        ProgressDialogUtil.hide();
                    }
                })
                .subscribe(new Observer<OwnpayRes>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d);
                        ProgressDialogUtil.show(OrderActivity.this);
                    }

                    @Override
                    public void onNext(@NonNull OwnpayRes ownpayRes) {
                        Intent intent = new Intent();
                        intent.putExtra("zhifubaourl", ownpayRes.getData().getAlipayUrl());
                        intent.putExtra("weixinurl", ownpayRes.getData().getWxPayUrl());
                        intent.putExtra("orderSn", ownpayRes.getData().getOrderSn());
                        intent.putExtra("finalPrice", ownpayRes.getData().getFinalPrice());
                        intent.putExtra("successPayUrl", ownpayRes.getData().getSuccessPayUrl());
                        intent.putExtra("citOrderSn", ownpayRes.getData().getCitOrderSn());
                        intent.putExtra("whereget", MyApplication.GetInstance().getUserSelectAir().name);
                        intent.setClass(OrderActivity.this, SelectPayTypeActivity.class);
                        startActivity(intent);
                        ShoppingCartMgr.getInstance().clear();
                        ShoppingCartMgr.getInstance().setColumns(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e instanceof ServiceMsgException) {
                            CustomAlertDialog customAlertDialog = new CustomAlertDialog();
                            customAlertDialog.setMsgInfo(e.getMessage());
                            customAlertDialog.show(getFragmentManager(), "customAlertDialog");
                        } else {
                            Toasty.error(OrderActivity.this, getString(R.string.netconnect_exception), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private class MyTimer extends CountDownTimer {

        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            getVaildateCode.setText("剩余" + millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            getVaildateCode.setEnabled(true);
            getVaildateCode.setClickable(true);
            getVaildateCode.setText("获取验证码");
        }
    }

    private void showSuccessUI() {
        View t = findViewById(R.id.button_success_icon);
        t.setVisibility(View.VISIBLE);

        t = findViewById(R.id.button_success_txt);
        t.setVisibility(View.VISIBLE);

        t = findViewById(R.id.toolbar119);
        t.setVisibility(View.GONE);

        t = findViewById(R.id.button_code_check);
        t.setVisibility(View.GONE);
    }

    private void setGoodsImages() {
        final int nCount = vaildateInfo.getGoodsSku().size();
        int nImageCount = 0;
        if (nCount >= 5) {
            nImageCount = 5;
        } else {
            nImageCount = nCount;
            findViewById(R.id.button_goods6).setVisibility(View.GONE);
        }

        try {
            int allBtn[] = new int[]{R.id.button_goods2, R.id.button_goods3, R.id.button_goods4, R.id.button_goods5, R.id.button_goods7};
            int allCover[] = new int[]{R.id.button_Cover2, R.id.button_Cover3, R.id.button_Cover4, R.id.button_Cover5, R.id.button_Cover7};

            for (int i = 0; i < nImageCount; i++) {
                VaildateInfo.GoodsSkuBean bean = vaildateInfo.getGoodsSku().get(i);
                ImageView imgView = (ImageView) findViewById(allBtn[i]);
                TextView btnCover = (TextView) findViewById(allCover[i]);
                if (imgView == null || btnCover == null)
                    continue;
                if (!TextUtils.isEmpty(bean.getGoods_img())) {
                    Picasso.with(OrderActivity.this).load(bean.getGoods_img()).into(imgView);
                }
                String strT = bean.getTax_display_txt();
                btnCover.setVisibility(!TextUtils.isEmpty(strT) ? View.VISIBLE : View.INVISIBLE);
                imgView.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setMoney() {
        String str;

        String strMoney = getResources().getString(R.string.server_settle_moeny);
        str = getResources().getString(R.string.server_settle_goodsPrice) + "\n" + strMoney + vaildateInfo.getGoodsPrice();
        goodsPrice.setText(str);

        str = getResources().getString(R.string.server_settle_discountActive) + "\n" + strMoney + vaildateInfo.getDiscountActive();
        discount.setText(str);

        str = getResources().getString(R.string.server_settle_1) + "\n" + strMoney + vaildateInfo.getFullActive();
        fullActive.setText(str);

        everyCountActive.setText("满件折优惠" + "\n" + strMoney + vaildateInfo.getEveryDiscountActive());

        everyFullActive.setText("每满减优惠" + "\n" + strMoney + vaildateInfo.getEveryFullActive());

        double couponPrice = 0.00;
        for (Coupon coupon : couponFragment.getCoupons()){
            if(coupon.isEnable() && coupon.isUsed()){
                couponPrice +=  coupon.getReduce_money();
            }
        }
        str = getResources().getString(R.string.server_settle_moeny1) + "\n" + String.format("%.2f",couponPrice);
        counpon.setText(str);

        double giftcardPrice = 0.00;
        for (Giftcard card : giftcardFragment.getCards()){
            if(card.isUsed() && card.getItemType() == Giftcard.CARD){
                giftcardPrice += card.getMoney();
            }
        }
        str = getResources().getString(R.string.server_settle_card) + "\n" + strMoney + String.format("%.2f",giftcardPrice);
        giftCard.setText(str);

        double bounsPrice = 0.00;
        for (Bouns bouns : bounsFragment.getBounses()){
            if(bouns.isUsed()){
                bounsPrice += bouns.getMoney();
            }
        }
        str = getResources().getString(R.string.server_settle_hongbao) + "\n" + strMoney + String.format("%.2f",bounsPrice);
        hongbao.setText(str);

        str = getResources().getString(R.string.server_settle_money_rate) + "\n" + strMoney + vaildateInfo.getMoney_rate();
        tax.setText(str);

        double thePrice = getFianlPrice();
        str = getResources().getString(R.string.server_settle_finalPrice) + "\n" + strMoney + String.format("%.2f",thePrice);
        finalPrice.setText(str);

        str = getResources().getString(R.string.server_settle_finalPrice) + "  " + getResources().getString(R.string.server_settle_moeny) + String.format("%.2f",thePrice);
        Spannable word = new SpannableString(str);
        word.setSpan(new AbsoluteSizeSpan(25), 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        bottomPrice.setText(word);
    }

    @android.support.annotation.NonNull
    private SpannableString getSpannableString(int avaliableCount,String name) {
        String countStr = String.valueOf(avaliableCount);
        String warnning = "有" + countStr + "可用的" + name;
        SpannableString spannableString = new SpannableString(warnning);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#C38C56")),1,1 + countStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableString;
    }

    @android.support.annotation.NonNull
    private SpannableStringBuilder getSpannableStringBuilder(int couponPrice, int usedCount) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String countStr = String.valueOf(usedCount);
        String warnning = "已选" + countStr + "张 :-" ;
        String priceStr = String.valueOf(couponPrice);
        String warnning2 = Constants.PRICE_TITLE + priceStr;
        SpannableString spannableString = new SpannableString(warnning);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#C38C56")),2,2 + countStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableStringBuilder.append(spannableString);
        SpannableString spannableString2 = new SpannableString(warnning2);
        spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#C38C56")),1,1 + priceStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableStringBuilder.append(spannableString2);
        return spannableStringBuilder;
    }
    private void freshCouponSelectedText(){
        double couponPrice = 0.00;
        boolean used = false;
        boolean avaliable = false;
        int usedCount = 0;
        int avaliableCount = 0;
        for (Coupon coupon : couponFragment.getCoupons()){
            if(coupon.isEnable() && coupon.isUsed() && coupon.getItemType() == Coupon.COUPON){
                used = true;
                couponPrice += coupon.getReduce_money();
                usedCount ++;
            }
            if(coupon.isEnable() && coupon.isAvailable() && coupon.getItemType() == Coupon.COUPON){
                avaliable = true;
                avaliableCount ++;
            }
        }
        if(used){
            SpannableStringBuilder spannableStringBuilder = getSpannableStringBuilder((int) couponPrice, usedCount);
            couponWarnning.setText(spannableStringBuilder);
        }else if(avaliable){
            SpannableString spannableString = getSpannableString(avaliableCount,"优惠券");
            couponWarnning.setText(spannableString);
        }else{
            couponWarnning.setText(getString(R.string.exchangedcoupon));
        }
    }

    private void freshGiftcardSelectedText(){
        double cardPrice = 0.00;
        boolean used = false;
        boolean avaliable = false;
        int usedCount = 0;
        int avaliableCount = 0;
        for (Giftcard card : giftcardFragment.getCards()){
            if(card.isUsed() && card.getItemType() == Giftcard.CARD){
                used = true;
                cardPrice += card.getMoney();
                usedCount ++;
            }
            if(card.isAvailable()&& card.getItemType() == Giftcard.CARD){
                avaliable = true;
                avaliableCount ++;
            }
        }
        if(used){
            SpannableStringBuilder spannableStringBuilder = getSpannableStringBuilder((int) cardPrice, usedCount);
            giftcardWarnning.setText(spannableStringBuilder);
        }else if(avaliable){
            SpannableString spannableString = getSpannableString(avaliableCount,"礼品卡");
            giftcardWarnning.setText(spannableString);
        }else{
            giftcardWarnning.setText(getString(R.string.use_giftcard));
        }
    }

    private void freshBounsSelectedText(){
        double bounsPrice = 0.00;
        boolean used = false;
        boolean avaliable = false;
        int usedCount = 0;
        int avaliableCount = 0;
        for (Bouns bouns : bounsFragment.getBounses()){
            if(bouns.isUsed() && bouns.getItemType() == Bouns.BOUNS){
                used = true;
                bounsPrice += bouns.getMoney();
                usedCount ++;
            }
            if(bouns.isAvailable() && bouns.getItemType() == Bouns.BOUNS){
                avaliable = true;
                avaliableCount ++;
            }
        }
        if(used){
            SpannableStringBuilder spannableStringBuilder = getSpannableStringBuilder((int) bounsPrice, usedCount);
            bounsWarnning.setText(spannableStringBuilder);
        }else if(avaliable){
            SpannableString spannableString = getSpannableString(avaliableCount,"红包");
            bounsWarnning.setText(spannableString);
        }else{
            bounsWarnning.setText(getString(R.string.no_bouns));
        }
    }
    private void freshSelectedText(){
        freshBounsSelectedText();
        freshCouponSelectedText();
        freshGiftcardSelectedText();
    }

    @Override
    public void onSelectBouns() {
        setMoney();
        couponFragment.onSelectedItemChange();
        giftcardFragment.onSelectedItemChange();
        freshSelectedText();
    }

    @Override
    public void calaAvaliable() {
        freshSelectedText();
    }

    @Override
    public DiscountReq getDiscountReq() {
        return getDiscountReq(null,false);
    }

    @Override
    public void onSelectedCoupon() {
        setMoney();
        giftcardFragment.onSelectedItemChange();
        bounsFragment.onSelectedItemChange();
    }

    @Override
    public void onSelectedGift() {
        setMoney();
        couponFragment.onSelectedItemChange();
        bounsFragment.onSelectedItemChange();
        freshSelectedText();
    }

    @Override
    public synchronized double getFianlPrice() {
        double orderPrice = Double.parseDouble(vaildateInfo.getFinalPrice());
        double thePrice = 0.00;
        for (Coupon coupon : couponFragment.getCoupons()){
            if(coupon.isUsed()){
                thePrice += coupon.getReduce_money();
            }
        }
        for (Giftcard card : giftcardFragment.getCards()){
            if(card.isUsed() && card.getItemType() == Giftcard.CARD){
                thePrice += card.getMoney();
            }
        }
        for (Bouns bouns : bounsFragment.getBounses()){
            if(bouns.isUsed()){
                thePrice += bouns.getMoney();
                break;
            }
        }
        return orderPrice - thePrice;
    }

}
