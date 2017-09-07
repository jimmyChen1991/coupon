package com.hhyg.TyClosing.ui.fragment.order;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import com.hhyg.TyClosing.exceptions.ServiceDataException;
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
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by user on 2017/8/28.
 */

public class CouponFragment extends BaseBottomDialogFragment {

    private static final String TAG = "CouponFragment";
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
    private ArrayList<Coupon> tmpCoupon = new ArrayList<>();

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

    public synchronized void onSelectedItemChange(ArrayList<Coupon> couponArrayList){
        double thePrice = orderPrice.getFianlPrice();
        for (Coupon coupon : couponArrayList){
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
                    calaDisable(true);
                    onSelectedItemChange(coupons);
                    couponOp.onSelectedCoupon();
                }else if(coupon.getItemType() == Coupon.COUPON && coupon.isEnable()){
                    if(coupon.isAvailable() && coupon.isPriceAvailable()){
                        if(coupon.isUsed()){
                            coupon.setUsed(false);
                            onSelectedItemChange(coupons);
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
                            makeCouponUsed(coupon,coupons,false);
                        }
                        calaDisable(false);
                    }
                }
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

    private void calaDisable(boolean disable){
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
            Toasty.warning(getActivity(),"请输入兑换码", Toast.LENGTH_SHORT).show();
        }else{
            if(couponOp.isVaildate()){
                Observable.just(couponOp.getDiscountReq())
                        .map(new Function<DiscountReq, ExchangecouponReq>() {
                            @Override
                            public ExchangecouponReq apply(@NonNull DiscountReq discountReq) throws Exception {
                                return getExchangecouponReq(discountReq);
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
                                final DiscountRes.DataBean.CouponsBean couponsBean = discountRes.getData().getCoupons();
                                addCoupon((ArrayList<CouponBean>) couponsBean.getUSABLE(),true);
                            }
                        })
                        .doOnNext(new Consumer<DiscountRes>() {
                            @Override
                            public void accept(@NonNull DiscountRes discountRes) throws Exception {
                                final DiscountRes.DataBean.CouponsBean couponsBean = discountRes.getData().getCoupons();
                               addCoupon((ArrayList<CouponBean>) couponsBean.getUNUSABLE(),false);
                            }
                        })
                        .doOnNext(new Consumer<DiscountRes>() {
                            @Override
                            public void accept(@NonNull DiscountRes discountRes) throws Exception {
                                initAvailable();
                            }
                        })
                        .doOnNext(new Consumer<DiscountRes>() {
                            @Override
                            public void accept(@NonNull DiscountRes discountRes) throws Exception {
                                setExchangedCouponUsed();
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                ProgressDialogUtil.hide();
                                tmpCoupon.clear();
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
                                coupons = (ArrayList<Coupon>) tmpCoupon.clone();
                                onSelectedItemChange(coupons);
                                couponOp.calaAvaliable();
                                adapter.setNewData(coupons);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                if(e instanceof ServiceMsgException){
                                    Toasty.error(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                }else {
                                    Toasty.error(getActivity(),getActivity().getString(R.string.netconnect_exception),Toast.LENGTH_SHORT).show();
                                }
                                printErr(e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }else{
                Observable.just(couponOp.getDiscountReq())
                        .map(new Function<DiscountReq, ExchangecouponReq>() {
                            @Override
                            public ExchangecouponReq apply(@NonNull DiscountReq discountReq) throws Exception {
                                return getExchangecouponReq(discountReq);
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
                                }else if(exchangecouponRes.getData().getCoupons() == null || exchangecouponRes.getData().getCoupons().getUSABLE().size() == 0){
                                    throw new ServiceDataException();
                                }
                            }
                        })
                        .doOnNext(new Consumer<ExchangecouponRes>() {
                            @Override
                            public void accept(@NonNull ExchangecouponRes exchangecouponRes) throws Exception {
                                ExchangecouponRes.DataBean.CouponsBean couponsBean = exchangecouponRes.getData().getCoupons();
                                final ArrayList<CouponBean> res = (ArrayList<CouponBean>) couponsBean.getUSABLE();
                                addCoupon(res,true);
                            }
                        })
                        .doOnNext(new Consumer<ExchangecouponRes>() {
                            @Override
                            public void accept(@NonNull ExchangecouponRes exchangecouponRes) throws Exception {
                                ExchangecouponRes.DataBean.CouponsBean couponsBean = exchangecouponRes.getData().getCoupons();
                                final ArrayList<CouponBean> res = (ArrayList<CouponBean>) couponsBean.getUNUSABLE();
                                addCoupon(res,false);
                            }
                        })
                        .doOnNext(new Consumer<ExchangecouponRes>() {
                            @Override
                            public void accept(@NonNull ExchangecouponRes exchangecouponRes) throws Exception {
                                initAvailable();
                            }
                        })
                        .doOnNext(new Consumer<ExchangecouponRes>() {
                            @Override
                            public void accept(@NonNull ExchangecouponRes exchangecouponRes) throws Exception {
                                setExchangedCouponUsed();
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                ProgressDialogUtil.hide();
                                tmpCoupon.clear();
                            }
                        })
                        .subscribe(new Observer<ExchangecouponRes>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                disposable = d;
                                ProgressDialogUtil.show(getActivity());
                            }

                            @Override
                            public void onNext(@NonNull ExchangecouponRes exchangecouponRes) {
                                Toasty.success(getActivity(),exchangecouponRes.getMsg(),Toast.LENGTH_SHORT).show();
                                if(tmpCoupon != null && tmpCoupon.size() != 0){
                                    rvWrap.setVisibility(View.VISIBLE);
                                    emptyView.setVisibility(View.GONE);
                                    coupons = (ArrayList<Coupon>) tmpCoupon.clone();
                                    onSelectedItemChange(coupons);
                                    couponOp.calaAvaliable();
                                    adapter.setNewData(coupons);
                                    adapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                if(e instanceof ServiceMsgException){
                                    Toasty.error(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                }else if(e instanceof ServiceDataException){
                                    Toasty.error(getActivity(),getString(R.string.server_exception),Toast.LENGTH_SHORT).show();
                                }else{
                                    Toasty.error(getActivity(),getString(R.string.netconnect_exception),Toast.LENGTH_SHORT).show();
                                }
                                Log.d("CouponFragment", e.toString());

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }
    }

    private void setExchangedCouponUsed() {
        if(tmpCoupon.size() != 0){
            for (Coupon coupon : tmpCoupon){
                if(coupon.getItemType() == Coupon.COUPON && coupon.isEnable() && coupon.getCode_str().equals(couponId.getText().toString())){
                    if(coupon.isAvailable() && coupon.isPriceAvailable()){
                        makeCouponUsed(coupon,tmpCoupon,false);
                    }
                }
            }
        }
    }

    private void initAvailable() {
        if(coupons.size() != 0){
            Log.d("CouponFragment", "next4");
            for (Coupon coupon : coupons){
                if(coupon.getItemType() == Coupon.COUPON && coupon.isEnable() && coupon.isUsed()){
                    for (Coupon tmpBean : tmpCoupon){
                        if(tmpBean.isEnable() && tmpBean.getItemType() == Coupon.COUPON && tmpBean.getCode_str().equals(coupon.getCode_str())){
                            makeCouponUsed(tmpBean,tmpCoupon,false);
                        }
                    }
                }
            }
        }
    }

    private void makeCouponUsed(Coupon coupon,ArrayList<Coupon> couponArrayList,boolean forinit) {
        coupon.setUsed(true);
        if(!forinit){
            onSelectedItemChange(couponArrayList);
            couponOp.onSelectedCoupon();
        }
        if(coupon.getIsEntire() == 1){
            for (Coupon bean : couponArrayList){
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
                        for (Coupon bean : couponArrayList){
                            if((bean.getItemType() == Coupon.COUPON  && bean.isEnable() && bean.getCode_str().equals(conflict) && !bean.isUsed()) || (bean.isEnable() && bean.getItemType() == Coupon.COUPON&& bean.getIsEntire() == 1)){
                                bean.setAvailable(false);
                                bean.setUnavailableReason("与已选优惠券商品范围重叠");
                            }

                        }

                    }
                }
            }

        }
    }

    private void addCoupon(ArrayList<CouponBean> res,boolean useable) {
        if(res != null && res.size() != 0){
            Coupon title = new Coupon(Coupon.TITLE);
            if(useable){
                title.setEnable(true);
            }
            title.setCount(res.size());
            tmpCoupon.add(title);
            for (int index = 0; index < res.size(); index++) {
                CouponBean data = res.get(index);
                Coupon coupon = new Coupon(Coupon.COUPON);
                CouponUtil.initCoupon(data, coupon);
                if(useable){
                    coupon.setAvailable(true);
                    coupon.setEnable(true);
                }
                tmpCoupon.add(coupon);
            }
            if(useable){
                Coupon disable = new Coupon(Coupon.DISABLE);
                disable.setEnable(true);
                tmpCoupon.add(disable);
            }
        }
    }

    @android.support.annotation.NonNull
    private ExchangecouponReq getExchangecouponReq(@NonNull DiscountReq discountReq) {
        ExchangecouponReq req = new ExchangecouponReq();
        req.setChannel(commonParam.getChannelId());
        req.setImei(commonParam.getImei());
        req.setPlatformId(commonParam.getPlatformId());
        req.setShopid(commonParam.getShopId());
        ExchangecouponReq.DataBean data = new ExchangecouponReq.DataBean();
        data.setExchangecode(couponId.getText().toString());
        data.setGoodslist(discountReq.getData().getGoodslist());
        data.setDeliverplace(discountReq.getData().getDeliverplace());
        data.setFinal_total_price(discountReq.getData().getFinal_total_price());
        data.setMobile_phone(discountReq.getData().getMobile_phone());
        req.setData(data);
        return req;
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

    private void printErr(@NonNull Throwable e) {
        for (int index = 0; index < e.getStackTrace().length; index++) {
            Log.d(TAG, e.getStackTrace()[index].toString());
        }
    }
}
