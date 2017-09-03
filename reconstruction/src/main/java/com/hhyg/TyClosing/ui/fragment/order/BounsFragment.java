package com.hhyg.TyClosing.ui.fragment.order;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.ui.fragment.BaseBottomDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by user on 2017/8/28.
 */

public class BounsFragment extends BaseBottomDialogFragment {

    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.rvWrap)
    LinearLayout rvWrap;
    Unbinder unbinder;

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_bouns;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.use_coupon)
    public void onViewClicked() {

    }

    @OnClick(R.id.close)
    public void onViewClicked1() {
        dismiss();
    }
}
