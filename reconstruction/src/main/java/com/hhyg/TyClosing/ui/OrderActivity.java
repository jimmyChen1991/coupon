package com.hhyg.TyClosing.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
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
import com.hhyg.TyClosing.di.componet.DaggerCommonNetParamComponent;
import com.hhyg.TyClosing.di.componet.DaggerOrderComponent;
import com.hhyg.TyClosing.di.module.CommonNetParamModule;
import com.hhyg.TyClosing.di.module.OrderModule;
import com.hhyg.TyClosing.entities.CommonParam;
import com.hhyg.TyClosing.entities.order.SecuryReq;
import com.hhyg.TyClosing.entities.order.SecuryRes;
import com.hhyg.TyClosing.entities.order.SendVaildateCodeReq;
import com.hhyg.TyClosing.entities.order.SendVaildateCodeRes;
import com.hhyg.TyClosing.entities.order.VaildateInfo;
import com.hhyg.TyClosing.exceptions.ServiceMsgException;
import com.hhyg.TyClosing.global.MyApplication;
import com.hhyg.TyClosing.info.GoodSku;
import com.hhyg.TyClosing.mgr.ClosingRefInfoMgr;
import com.hhyg.TyClosing.ui.fragment.AutoSettleOrderItemsFragment;
import com.hhyg.TyClosing.ui.fragment.order.BounsFragment;
import com.hhyg.TyClosing.ui.fragment.order.CouponFragment;
import com.hhyg.TyClosing.ui.fragment.order.GiftcardFragment;
import com.hhyg.TyClosing.util.ProgressDialogUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

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

public class OrderActivity extends AppCompatActivity {

    private static final String TAG = "OrderActivity";

    @Inject
    Gson gson;
    @Inject
    List<GoodSku> goodData;
    VaildateInfo vaildateInfo;
    @Inject
    CommonParam commonParam;
    @Inject
    OrderSevice orderSevice;
    @Inject
    CompositeDisposable disposables;
    @Inject
    CouponFragment couponFragment;
    @Inject
    BounsFragment bounsFragment;
    @Inject
    GiftcardFragment giftcardFragment;
    MyTimer mTimer = new MyTimer(60000,1000);
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
        if (ClosingRefInfoMgr.getInstance().getLoginConfig().isCard_active()) {
            giftEntry.setVisibility(View.VISIBLE);
        } else {
            getGiftSplit.setVisibility(View.GONE);
            giftEntry.setVisibility(View.GONE);
        }

        vaildateInfo = gson.fromJson(getIntent().getStringExtra("data"), VaildateInfo.class);

        if (vaildateInfo.getAvailable() == 1) {
            vipView.setVisibility(View.VISIBLE);
            vipName.setText("账户 ：" + vaildateInfo.getPhone());
        } else {
            vipView.setVisibility(View.GONE);
        }

        initUserInfo();
        setGoodsImages();
        AutoSettleOrderItemsFragment fragment =
                (AutoSettleOrderItemsFragment) (getFragmentManager().findFragmentById(R.id.orderDetailFragment));
        fragment.setData(goodData);
        setMoney();
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
    public void onViewClicked2(){
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    @OnClick({R.id.coupon_entry, R.id.bouns_entry, R.id.giftcard_entry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.coupon_entry:
                couponFragment.show(getSupportFragmentManager(),"coupon");
                break;
            case R.id.bouns_entry:
                bounsFragment.show(getSupportFragmentManager());
                break;
            case R.id.giftcard_entry:
                giftcardFragment.show(getSupportFragmentManager());
                break;
        }
    }

    @OnClick(R.id.button_get_code)
    public void onViewClicked3(final View view){
        view.setClickable(false);
        view.setEnabled(false);
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
                        return orderSevice.secury(gson.toJson(securyReq));
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
                        return orderSevice.sendVaildateCode(gson.toJson(sendVaildateCodeReq));
                    }
                })
                .doOnNext(new Consumer<SendVaildateCodeRes>() {
                    @Override
                    public void accept(@NonNull SendVaildateCodeRes sendVaildateCodeRes) throws Exception {
                        if(sendVaildateCodeRes.getErrcode() != 1){
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
                        Toasty.success(OrderActivity.this,sendVaildateCodeRes.getMsg(),Toast.LENGTH_SHORT).show();
                        mTimer.start();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if(e instanceof ServiceMsgException){
                            Toasty.error(OrderActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }else{
                            Toasty.error(OrderActivity.this,getString(R.string.netconnect_exception),Toast.LENGTH_SHORT).show();
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
    public void onViewClicked4(){
        final String input = vaildateCodeInput.getText().toString();
        if(!TextUtils.isEmpty(input)){

        }else{
            Toasty.warning(this,"请输入验证码", Toast.LENGTH_SHORT).show();
        }

    }

    private class MyTimer extends CountDownTimer{

        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            getVaildateCode.setText("剩余" + millisUntilFinished/1000 + "秒");
        }

        @Override
        public void onFinish() {
            getVaildateCode.setEnabled(true);
            getVaildateCode.setClickable(true);
            getVaildateCode.setText("获取验证码");
        }
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
                VaildateInfo.GoodsSkuBean bean = vaildateInfo.getGoodsSku().get(i) ;
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

        str = getResources().getString(R.string.server_settle_moeny1) + "\n" + strMoney;
        counpon.setText(str);

        str = getResources().getString(R.string.server_settle_card) + "\n" + strMoney;
        giftCard.setText(str);

        str = getResources().getString(R.string.server_settle_hongbao) + "\n" + strMoney;
        hongbao.setText(str);

        str = getResources().getString(R.string.server_settle_money_rate) + "\n" + strMoney + vaildateInfo.getMoney_rate();
        tax.setText(str);

        str = getResources().getString(R.string.server_settle_finalPrice) + "\n" + strMoney;
        finalPrice.setText(str);

        str = getResources().getString(R.string.server_settle_finalPrice) + "  " + getResources().getString(R.string.server_settle_moeny);
        Spannable word = new SpannableString(str);
        word.setSpan(new AbsoluteSizeSpan(25), 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        bottomPrice.setText(word);
    }
}
