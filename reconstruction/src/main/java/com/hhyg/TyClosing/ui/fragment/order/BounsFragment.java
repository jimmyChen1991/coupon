package com.hhyg.TyClosing.ui.fragment.order;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.entities.order.Bouns;
import com.hhyg.TyClosing.entities.order.Giftcard;
import com.hhyg.TyClosing.mgr.OrderPrice;
import com.hhyg.TyClosing.ui.adapter.order.BounsAdapter;
import com.hhyg.TyClosing.ui.fragment.BaseBottomDialogFragment;

import java.util.ArrayList;

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
    private BounsAdapter adapter;
    private ArrayList<Bouns> bounses = new ArrayList<>();
    private OrderPrice orderPrice;
    private BounsOp bounsOp;

    public ArrayList<Bouns> getBounses() {
        return bounses;
    }

    public void setOrderPrice(OrderPrice orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setBounsOp(BounsOp bounsOp) {
        this.bounsOp = bounsOp;
    }

    public void setBounses(ArrayList<Bouns> bounses) {
        this.bounses = bounses;
    }

    public void addBouns(Bouns bouns){
        if(bounses.size() == 0){
            bounses.add(bouns);
            Bouns disableBouns = new Bouns(Bouns.DISABLE);
            bounses.add(disableBouns);
        }else{
            bounses.add(bounses.size() - 1, bouns);
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_bouns;
    }

    @Override
    public void bindView(View v) {
        unbinder = ButterKnife.bind(this, v);
        rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new BounsAdapter(bounses);
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                Bouns bouns = bounses.get(position);
                int count = 0;
                if(bouns != null){
                    if(bouns.getItemType() == Giftcard.CARD){
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
                Bouns item = bounses.get(position);
                if(item.getItemType() == Bouns.DISABLE){
                    for (Bouns bean : bounses){
                        if(bean.isUsed()){
                            bean.setUsed(false);
                            onSelectedItemChange();
                            bounsOp.onSelectBouns();
                            break;
                        }
                    }
                }else{
                    if(item.isAvailable()){
                        if(item.isUsed()){
                            item.setUsed(false);
                        }else{
                            for(Bouns bean : bounses){
                                bean.setUsed(false);
                            }
                            item.setUsed(true);
                        }
                        onSelectedItemChange();
                        bounsOp.onSelectBouns();
                    }
                }
            }
        });
        rv.setAdapter(adapter);
        if(bounses.size()!= 0){
            rvWrap.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }else{
            rvWrap.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    public synchronized void onSelectedItemChange(){
        double thePrice = orderPrice.getFianlPrice();
        for (Bouns bouns : bounses){
            if(bouns.getMoney() <= thePrice){
                bouns.setAvailable(false);
            }else {
                bouns.setAvailable(true);
            }
        }
        adapter.notifyDataSetChanged();
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

    @OnClick(R.id.use_bouns)
    public void onViewClicked() {
        dismiss();
    }

    @OnClick(R.id.close)
    public void onViewClicked1() {
        dismiss();
    }

    public interface BounsOp{
        void onSelectBouns();
    }
}
