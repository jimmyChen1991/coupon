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
import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.apiService.OrderSevice;
import com.hhyg.TyClosing.entities.CommonParam;
import com.hhyg.TyClosing.entities.order.Bouns;
import com.hhyg.TyClosing.entities.order.Coupon;
import com.hhyg.TyClosing.entities.order.Giftcard;
import com.hhyg.TyClosing.ui.adapter.order.BounsAdapter;
import com.hhyg.TyClosing.ui.adapter.order.CouponAdapter;
import com.hhyg.TyClosing.ui.fragment.BaseBottomDialogFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import io.reactivex.disposables.Disposable;


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

        }
    }

    @OnClick(R.id.use_coupon)
    public void onViewClicked2(){
        dismiss();
    }

    public interface CouponOp{

        void exchangeCoupon(final String code);
    }

}
