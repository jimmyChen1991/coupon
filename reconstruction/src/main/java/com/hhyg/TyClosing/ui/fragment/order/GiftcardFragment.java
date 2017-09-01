package com.hhyg.TyClosing.ui.fragment.order;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.apiService.OrderSevice;
import com.hhyg.TyClosing.entities.CommonParam;
import com.hhyg.TyClosing.entities.order.SearchGiftCardReq;
import com.hhyg.TyClosing.entities.order.SearchGiftCardRes;
import com.hhyg.TyClosing.mgr.ClosingRefInfoMgr;
import com.hhyg.TyClosing.ui.fragment.BaseBottomDialogFragment;

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
    @BindView(R.id.close)
    ImageButton close;
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
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "close");
        unbinder.unbind();
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
                            SearchGiftCardReq.DataBean data = new SearchGiftCardReq.DataBean();
                            data.setGiftCode(giftcardId.getText().toString());
                            data.setGiftPwd(giftPwd.getText().toString());
                            data.setGiftKey(token);
                            data.setOrderPrice("0");
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
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SearchGiftCardRes>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull SearchGiftCardRes searchGiftCardRes) {

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

}
