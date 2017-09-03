package com.hhyg.TyClosing.ui.fragment.order;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.apiService.OrderSevice;
import com.hhyg.TyClosing.entities.CommonParam;
import com.hhyg.TyClosing.ui.fragment.BaseBottomDialogFragment;

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

}
