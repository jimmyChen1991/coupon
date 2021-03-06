package com.hhyg.TyClosing.ui.fragment.order;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.apiService.OrderSevice;
import com.hhyg.TyClosing.entities.CommonParam;
import com.hhyg.TyClosing.entities.order.CheckGiftcardReq;
import com.hhyg.TyClosing.entities.order.CheckGiftcardRes;
import com.hhyg.TyClosing.entities.order.Giftcard;
import com.hhyg.TyClosing.entities.order.SearchGiftCardReq;
import com.hhyg.TyClosing.entities.order.SearchGiftCardRes;
import com.hhyg.TyClosing.exceptions.ServiceMsgException;
import com.hhyg.TyClosing.mgr.ClosingRefInfoMgr;
import com.hhyg.TyClosing.mgr.OrderPrice;
import com.hhyg.TyClosing.ui.adapter.order.GiftcardAdapter;
import com.hhyg.TyClosing.ui.fragment.BaseBottomDialogFragment;
import com.hhyg.TyClosing.util.ProgressDialogUtil;
import com.hhyg.TyClosing.util.SpannableUtil;
import com.hhyg.TyClosing.util.TimeUtill;

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
 * Created by user on 2017/8/30.
 */

public class GiftcardFragment extends BaseBottomDialogFragment {
    private static final String TAG = "GiftcardFragment";

    /*
        3486296095304561962
        0223834159143908554
        4176738717711533959
        3491620945386126766
        5362123686915368065
        1139888382329174782
        7287114901994710909
        5133153244668215263
        7926983897248028992

        200226

         http://wangwangexps.mianshui365.net/api/MSService.php?r=giftcard/search
        {"device_type":"android","platformId":3,"imei":"11391e0ce75b9f42","data":{"orderPrice":"830.00","giftKey":"","giftCode":"7926983897248028992","giftPwd":"200226"},"shopid":"1","op":"getgiftcard","channel":673,"saleId":"8"}
     */
    @BindView(R.id.giftcard_id)
    EditText giftcardId;
    @BindView(R.id.gift_pwd)
    EditText giftPwd;
    @BindView(R.id.check_card)
    Button checkCard;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.use_card)
    Button useCard;
    @BindView(R.id.rvWrap)
    LinearLayout rvWrap;
    Unbinder unbinder;
    private String token;
    private CommonParam commonParam;
    private OrderSevice indexSevice;
    private OrderSevice msSevice;
    private Disposable disposable;
    private ArrayList<Giftcard> cards = new ArrayList<>();
    private GiftcardAdapter adapter;
    private GiftcardOp giftcardOp;
    private OrderPrice orderPrice;

    public void setOrderPrice(OrderPrice orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setGiftcardListener(GiftcardOp giftcardOp) {
        this.giftcardOp = giftcardOp;
    }

    public ArrayList<Giftcard> getCards() {
        return cards;
    }

    public String getToken() {
        return token;
    }

    public void setCommonParam(CommonParam commonParam) {
        this.commonParam = commonParam;
    }

    public void setIndexSevice(OrderSevice indexSevice) {
        this.indexSevice = indexSevice;
    }

    public void setMsSevice(OrderSevice msSevice) {
        this.msSevice = msSevice;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_giftcard;
    }

    @Override
    public void bindView(View v) {
        Log.d(TAG, "create");
        unbinder = ButterKnife.bind(this, v);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        adapter = new GiftcardAdapter(getActivity(),cards);
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                Giftcard giftcard = cards.get(position);
                int count = 0;
                if(giftcard != null){
                    if(giftcard.getItemType() == Giftcard.CARD){
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
                Giftcard card = cards.get(position);
                if(card.getItemType() != Giftcard.CARD){

                }else{
                    if(card.isAvailable()){
                        checkTheCard(card);
                    }
                }
            }
        });
        rv.setAdapter(adapter);
        if(cards.size()!= 0){
            rvWrap.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }else{
            rvWrap.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    private void checkTheCard(final Giftcard giftcard) {
        Observable.just(giftcard)
                .map(new Function<Giftcard, CheckGiftcardReq>() {
                    @Override
                    public CheckGiftcardReq apply(@NonNull Giftcard giftcard) throws Exception {
                        CheckGiftcardReq req = new CheckGiftcardReq();
                        CheckGiftcardReq.DataBean data = new CheckGiftcardReq.DataBean();
                        req.setImei(commonParam.getImei());
                        req.setShopid(commonParam.getShopId());
                        req.setChannel(commonParam.getChannelId());
                        req.setPlatformId(commonParam.getPlatformId());
                        req.setSaleId(ClosingRefInfoMgr.getInstance().getSalerId());
                        data.setCheckFlag(giftcard.isUsed() ? 0 : 1);
                        data.setGiftCode(giftcard.getBarcode());
                        data.setGiftKey(token);
                        req.setData(data);
                        return req;
                    }
                })
                .flatMap(new Function<CheckGiftcardReq, ObservableSource<CheckGiftcardRes>>() {
                    @Override
                    public ObservableSource<CheckGiftcardRes> apply(@NonNull CheckGiftcardReq checkGiftcardReq) throws Exception {
                        Gson gson = new Gson();
                        return indexSevice.setCardStatus(gson.toJson(checkGiftcardReq));
                    }
                })
                .doOnNext(new Consumer<CheckGiftcardRes>() {
                    @Override
                    public void accept(@NonNull CheckGiftcardRes checkGiftcardRes) throws Exception {
                        if(checkGiftcardRes.getErrcode() != 1){
                            throw new ServiceMsgException(checkGiftcardRes.getMsg());
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
                .subscribe(new Observer<CheckGiftcardRes>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                        ProgressDialogUtil.show(getActivity());
                    }

                    @Override
                    public void onNext(@NonNull CheckGiftcardRes checkGiftcardRes) {
                        Toasty.success(getActivity(),checkGiftcardRes.getMsg(),Toast.LENGTH_SHORT).show();
                        giftcard.setUsed(checkGiftcardRes.getData().getCheckFlag() == 1 );
                        onSelectedItemChange();
                        giftcardOp.onSelectedGift();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, e.toString());
                        if(e instanceof ServiceMsgException){
                            Toasty.error(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }else {
                            Toasty.error(getActivity(),getString(R.string.netconnect_exception),Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public synchronized void onSelectedItemChange(){
        double thePrice = orderPrice.getFianlPrice();
        for (Giftcard giftcard : cards){
            if(!giftcard.isUsed() && thePrice < giftcard.getMoney()){
                giftcard.setAvailable(false);
            }else {
                giftcard.setAvailable(true);
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
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "close");
        giftcardId.getText().clear();
        giftPwd.getText().clear();
        unbinder.unbind();
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @OnClick(R.id.close)
    public void onViewClicked() {
        dismiss();
    }

    @OnClick(R.id.check_card)
    public void onViewClicked1(){
        if(TextUtils.isEmpty(giftcardId.getText().toString())){
            Toasty.warning(getActivity(),"请输入礼品卡号", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(giftPwd.getText().toString())){
            Toasty.warning(getActivity(),"请输入礼品卡密码", Toast.LENGTH_SHORT).show();
        }else {
            Observable.just(commonParam)
                    .map(new Function<CommonParam, SearchGiftCardReq>() {
                        @Override
                        public SearchGiftCardReq apply(@NonNull CommonParam param) throws Exception {
                            SearchGiftCardReq req = new SearchGiftCardReq();
                            req.setSaleId(ClosingRefInfoMgr.getInstance().getSalerId());
                            req.setShopid(param.getShopId());
                            req.setOp("getgiftcard");
                            req.setPlatformId(param.getPlatformId());
                            req.setChannel(param.getChannelId());
                            req.setImei(param.getImei());
                            req.setDevice_type("android");
                            SearchGiftCardReq.DataBean data = new SearchGiftCardReq.DataBean();
                            data.setGiftCode(giftcardId.getText().toString());
                            data.setGiftPwd(giftPwd.getText().toString());
                            data.setGiftKey(token);
                            data.setOrderPrice(orderPrice.getFianlPrice());
                            req.setData(data);
                            return req;
                        }
                    })
                    .flatMap(new Function<SearchGiftCardReq, ObservableSource<SearchGiftCardRes>>() {
                        @Override
                        public ObservableSource<SearchGiftCardRes> apply(@NonNull SearchGiftCardReq searchGiftCardReq) throws Exception {
                            Gson gson = new Gson();
                            return msSevice.searchGiftCard(gson.toJson(searchGiftCardReq));
                        }
                    })
                    .doOnNext(new Consumer<SearchGiftCardRes>() {
                        @Override
                        public void accept(@NonNull SearchGiftCardRes searchGiftCardRes) throws Exception {
                            if(searchGiftCardRes.getErrcode() != 1){
                                throw new ServiceMsgException(searchGiftCardRes.getMsg());
                            }
                        }
                    })
                    .map(new Function<SearchGiftCardRes, Giftcard>() {
                        @Override
                        public Giftcard apply(@NonNull SearchGiftCardRes searchGiftCardRes) throws Exception {
                            final SearchGiftCardRes.DataBean bean = searchGiftCardRes.getData();
                            if(TextUtils.isEmpty(token)){
                                token = bean.getTemp_order_key();
                            }
                            Giftcard card = new Giftcard(Giftcard.CARD);
                            SpannableString spannableString = SpannableUtil.discountSpan(bean.getMoney());
                            card.setSpannableString(spannableString);
                            card.setBarcode(bean.getBarcode());
                            card.setMoney(bean.getMoney());
                            card.setTime_begin(TimeUtill.TimeStamp2Date(bean.getTime_begin()));
                            card.setTime_end(TimeUtill.TimeStamp2Date(bean.getTime_end()));
                            card.setTimeTv(TimeUtill.TimeStamp2Date(bean.getTime_begin()) +" ~ " + TimeUtill.TimeStamp2Date(bean.getTime_end()));
                            card.setBottemContent(TimeUtill.TimeStamp2Date(bean.getTime_begin()) +" ~ " + TimeUtill.TimeStamp2Date(bean.getTime_end()));
                            card.setUnavailableReason("优惠金额大于订单实付金额");
                            double thePrice = orderPrice.getFianlPrice();
                            if(thePrice <= bean.getMoney()){
                                card.setAvailable(false);
                            }else{
                                card.setAvailable(true);
                            }
                            return card;
                        }
                    })
                    .doOnNext(new Consumer<Giftcard>() {
                        @Override
                        public void accept(@NonNull Giftcard giftcard) throws Exception {
                            cards.add(giftcard);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Giftcard>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            disposable = d;
                            ProgressDialogUtil.show(getActivity());
                        }

                        @Override
                        public void onNext(@NonNull Giftcard giftcard) {
                            adapter.notifyDataSetChanged();
                            emptyView.setVisibility(View.GONE);
                            rvWrap.setVisibility(View.VISIBLE);
                            if(giftcard.getMoney() < orderPrice.getFianlPrice()){
                                checkTheCard(giftcard);
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            if(e instanceof ServiceMsgException){
                                Toasty.error(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                            }else {
                                Toasty.error(getActivity(),getActivity().getString(R.string.netconnect_exception),Toast.LENGTH_SHORT).show();
                            }
                            ProgressDialogUtil.hide();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

    @OnClick(R.id.use_card)
    public void onViewClicked2(){
        dismiss();
    }

    public interface GiftcardOp{
        void onSelectedGift();
    }
}
