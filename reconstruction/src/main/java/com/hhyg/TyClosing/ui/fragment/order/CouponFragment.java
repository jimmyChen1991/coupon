package com.hhyg.TyClosing.ui.fragment.order;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.apiService.OrderSevice;
import com.hhyg.TyClosing.entities.CommonParam;
import com.hhyg.TyClosing.entities.order.Bouns;
import com.hhyg.TyClosing.entities.order.Coupon;
import com.hhyg.TyClosing.entities.order.CouponBean;
import com.hhyg.TyClosing.entities.order.DiscountReq;
import com.hhyg.TyClosing.entities.order.DiscountRes;
import com.hhyg.TyClosing.entities.order.ExchangecouponReq;
import com.hhyg.TyClosing.entities.order.ExchangecouponRes;
import com.hhyg.TyClosing.entities.order.Giftcard;
import com.hhyg.TyClosing.exceptions.ServiceMsgException;
import com.hhyg.TyClosing.mgr.OrderPrice;
import com.hhyg.TyClosing.ui.adapter.order.BounsAdapter;
import com.hhyg.TyClosing.ui.adapter.order.CouponAdapter;
import com.hhyg.TyClosing.ui.fragment.BaseBottomDialogFragment;
import com.hhyg.TyClosing.util.CouponUtil;
import com.hhyg.TyClosing.util.ProgressDialogUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by user on 2017/8/28.
 */

public class CouponFragment extends BaseBottomDialogFragment {

    @BindView(R.id.coupon_id)
    EditText couponId;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.rvWrap)
    LinearLayout rvWrap;
    Unbinder unbinder;
    private Disposable disposable;
    private CommonParam commonParam;
    private OrderSevice indexSevice;
    private ArrayList<Coupon> coupons = new ArrayList<>();
    private CouponAdapter adapter;
    private CouponOp couponOp;
    private OrderPrice orderPrice;

    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    public void setOrderPrice(OrderPrice orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setCouponOp(CouponOp couponOp) {
        this.couponOp = couponOp;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    public void addCoupon(Coupon coupon){
        coupons.add(coupon);
    }

    public void setCommonParam(CommonParam commonParam) {
        this.commonParam = commonParam;
    }

    public void setIndexSevice(OrderSevice indexSevice) {
        this.indexSevice = indexSevice;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_coupon;
    }

    public synchronized void onSelectedItemChange(){
        double thePrice = orderPrice.getFianlPrice();
        for (Coupon coupon : coupons){
            if(coupon.getItemType() == Coupon.COUPON && coupon.isEnable()){
                if(!coupon.isUsed() && thePrice < coupon.getReduce_money()){
                    coupon.setPriceAvailable(false);
                    coupon.setUnavailableReason("面额大于实付金额");
                }else{
                    coupon.setPriceAvailable(true);
                }
            }
        }
    }

    @Override
    public void bindView(View v) {
        unbinder = ButterKnife.bind(this, v);
        rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new CouponAdapter(coupons);
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                Coupon coupon = coupons.get(position);
                int count = 0;
                if(coupon != null){
                    if(coupon.getItemType() == Coupon.COUPON){
                        count = 1;
                    }else{
                        count = 2;
                    }
                }
                return count;
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Coupon coupon = coupons.get(position);
                if(coupon.getItemType() == Coupon.COUPON && coupon.isEnable()){
                    if(!coupon.isAvailable()){
                        Toasty.warning(getActivity(),"与已勾选的券有商品范围重叠，请先取消已有勾选").show();
                    }else if(!coupon.isPriceAvailable()){
                        Toasty.warning(getActivity(),"优惠券金额大于订单目前实付金额，不可选择",Toast.LENGTH_SHORT).show();
                    }
                }
                if(coupon.getItemType() == Coupon.DISABLE){
                    for (Coupon bean : coupons){
                        if(bean.getItemType() == Coupon.COUPON && bean.isEnable()){
                            bean.setUsed(false);
                            bean.setAvailable(true);
                        }
                    }
                    onSelectedItemChange();
                    couponOp.onSelectedCoupon();
                }else if(coupon.getItemType() == Coupon.COUPON && coupon.isEnable()){
                    if(coupon.isAvailable() && coupon.isPriceAvailable()){
                        if(coupon.isUsed()){
                            coupon.setUsed(false);
                            onSelectedItemChange();
                            couponOp.onSelectedCoupon();
                            if(coupon.getIsEntire() == 1){
                                for (Coupon bean : coupons){
                                    if(bean.getItemType() == Coupon.COUPON && bean.isEnable()){
                                        bean.setAvailable(true);
                                    }
                                }
                            }else{
                                boolean using = false;
                                for (Coupon bean : coupons){
                                    if(bean.isEnable() && bean.getItemType() == Coupon.COUPON && bean.isUsed()){
                                        using = true;
                                        break;
                                    }
                                }
                                if(!using){
                                    for (Coupon bean : coupons){
                                        if(bean.getItemType() == Coupon.COUPON && bean.isEnable()){
                                            bean.setAvailable(true);
                                        }
                                    }
                                }
                                else if(coupon.getConflict() != null && coupon.getConflict().size() != 0){
                                    for (String conflict : coupon.getConflict()){
                                        if(!conflict.equals(coupon.getCode_str())){
                                            boolean enable = true;
                                            for (Coupon bean : coupons){
                                                if(bean.getItemType() == Coupon.COUPON && bean.isEnable() && bean.isUsed() && bean.getConflict() != null && bean.getConflict().size() != 0){
                                                    for (String tmpConflict : bean.getConflict()){
                                                        if(!tmpConflict.equals(bean.getCode_str()) && tmpConflict.equals(conflict)){
                                                            enable = false;
                                                            break;
                                                        }
                                                    }

                                                }

                                            }
                                            if(enable){
                                                for (Coupon bean : coupons){
                                                    if(bean.getItemType() == Coupon.COUPON  && bean.isEnable() && bean.getCode_str().equals(conflict)){
                                                        bean.setAvailable(true);
                                                        break;
                                                    }

                                                }
                                            }

                                        }

                                    }
                                }
                            }

                        }else {
                            coupon.setUsed(true);
                            onSelectedItemChange();
                            couponOp.onSelectedCoupon();
                            if(coupon.getIsEntire() == 1){
                                for (Coupon bean : coupons){
                                    if(bean.getItemType() == Coupon.COUPON && bean.isEnable() && bean.isAvailable()){
                                        bean.setAvailable(false);
                                        bean.setUnavailableReason("与已选优惠券商品范围重叠");
                                    }
                                }
                                coupon.setAvailable(true);
                            }else{
                                if(coupon.getConflict() != null && coupon.getConflict().size() != 0){
                                    for (String conflict : coupon.getConflict()){
                                        if(!conflict.equals(coupon.getCode_str())){
                                            for (Coupon bean : coupons){
                                                if((bean.getItemType() == Coupon.COUPON  && bean.isEnable() && bean.getCode_str().equals(conflict) && !bean.isUsed()) || (bean.getIsEntire() == 1)){
                                                    bean.setAvailable(false);
                                                    bean.setUnavailableReason("与已选优惠券商品范围重叠");
                                                }

                                            }

                                        }
                                    }
                                }

                            }

                        }
                    }
                }
                calaDisable();
                adapter.notifyDataSetChanged();
                couponOp.calaAvaliable();
            }
        });
        rv.setAdapter(adapter);
        if(coupons.size()!= 0){
            rvWrap.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }else{
            rvWrap.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    private void calaDisable(){
        boolean disable = true;
        for (Coupon coupon : coupons){
            if(coupon.getItemType() == Coupon.COUPON && coupon.isEnable() && coupon.isUsed()){
                disable = false;
                break;
            }
        }
        if(disable){
            for (Coupon coupon : coupons){
                if(coupon.getItemType() == Coupon.DISABLE){
                    coupon.setDiableUsed(true);
                    break;
                }
            }
        }else {
            for (Coupon coupon : coupons){
                if(coupon.getItemType() == Coupon.DISABLE){
                    coupon.setDiableUsed(false);
                    break;
                }
            }
        }
    }

    @Override
    public int getHeight() {
        return 1160;
    }

    @Override
    public float getDimAmount() {
        return (float) 0.5;
    }

    @Override
    public boolean getCancelOutside() {
        return true;
    }

    @Override
    public String getFragmentTag() {
        return super.getFragmentTag();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @OnClick(R.id.close)
    public void onViewClicked() {
        dismiss();
    }

    @OnClick(R.id.check_coupon)
    public void onViewClicked1(){
        if(TextUtils.isEmpty(couponId.getText().toString())){
            Toasty.warning(getActivity(),"请输入优惠券", Toast.LENGTH_SHORT).show();
        }else{
            final ArrayList<Coupon> tmpCoupon = new ArrayList<>();
            Observable.just(couponId.getText().toString())
                    .map(new Function<String, ExchangecouponReq>() {
                        @Override
                        public ExchangecouponReq apply(@NonNull String s) throws Exception {
                            ExchangecouponReq req = new ExchangecouponReq();
                            req.setChannel(commonParam.getChannelId());
                            req.setImei(commonParam.getImei());
                            req.setPlatformId(commonParam.getPlatformId());
                            req.setShopid(commonParam.getShopId());
                            return req;
                        }
                    })
                    .flatMap(new Function<ExchangecouponReq, ObservableSource<ExchangecouponRes>>() {
                        @Override
                        public ObservableSource<ExchangecouponRes> apply(@NonNull ExchangecouponReq exchangecouponReq) throws Exception {
                            Gson gson = new Gson();
                            return indexSevice.exchangeCoupon(gson.toJson(exchangecouponReq));
                        }
                    })
                    .doOnNext(new Consumer<ExchangecouponRes>() {
                        @Override
                        public void accept(@NonNull ExchangecouponRes exchangecouponRes) throws Exception {
                            if(exchangecouponRes.getErrcode() != 1){
                                throw new ServiceMsgException(exchangecouponRes.getMsg());
                            }
                        }
                    })
                    .map(new Function<ExchangecouponRes, DiscountReq>() {
                        @Override
                        public DiscountReq apply(@NonNull ExchangecouponRes exchangecouponRes) throws Exception {
                            return couponOp.getDiscountReq();
                        }
                    })
                    .flatMap(new Function<DiscountReq, ObservableSource<DiscountRes>>() {
                        @Override
                        public ObservableSource<DiscountRes> apply(@NonNull DiscountReq discountReq) throws Exception {
                            Gson gson = new Gson();
                            return indexSevice.getDiscount(gson.toJson(discountReq));
                        }
                    })
                    .doOnNext(new Consumer<DiscountRes>() {
                        @Override
                        public void accept(@NonNull DiscountRes discountRes) throws Exception {
                            if(discountRes.getErrcode() != 1){
                                throw new ServiceMsgException(discountRes.getMsg());
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
                                tmpCoupon.add(title);
                                for (int index = 0; index < couponsBean.getUSABLE().size(); index++) {
                                    CouponBean res = couponsBean.getUSABLE().get(index);
                                    Coupon coupon = new Coupon(Coupon.COUPON);
                                    CouponUtil.initCoupon(res, coupon);
                                    tmpCoupon.add(coupon);
                                }
                                Coupon disable = new Coupon(Coupon.DISABLE);
                                disable.setEnable(true);
                                tmpCoupon.add(disable);
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
                                tmpCoupon.add(title);
                                for (int index = 0; index < couponsBean.getUNUSABLE().size(); index++) {
                                    CouponBean res = couponsBean.getUNUSABLE().get(index);
                                    Coupon coupon = new Coupon(Coupon.COUPON);
                                    coupon.setEnable(false);
                                    CouponUtil.initCoupon(res,coupon);
                                    tmpCoupon.add(coupon);
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
                            disposable = d;
                            ProgressDialogUtil.show(getActivity());
                        }

                        @Override
                        public void onNext(@NonNull DiscountRes discountRes) {
                            coupons = tmpCoupon;
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            if(e instanceof ServiceMsgException){
                                Toasty.error(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                            }else {
                                Toasty.error(getActivity(),getActivity().getString(R.string.netconnect_exception),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
    }

    @OnClick(R.id.use_coupon)
    public void onViewClicked2(){
        dismiss();
    }

    public interface CouponOp{

        void onSelectedCoupon();

        DiscountReq getDiscountReq();

        void calaAvaliable();

        boolean isVaildate();
    }

}
