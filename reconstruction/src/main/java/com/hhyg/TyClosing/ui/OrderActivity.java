package com.hhyg.TyClosing.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.di.componet.DaggerCommonNetParamComponent;
import com.hhyg.TyClosing.di.componet.DaggerOrderComponent;
import com.hhyg.TyClosing.di.module.CommonNetParamModule;
import com.hhyg.TyClosing.entities.order.VaildateInfo;
import com.hhyg.TyClosing.global.MyApplication;
import com.hhyg.TyClosing.mgr.ClosingRefInfoMgr;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends AppCompatActivity {

    private static final String TAG = "OrderActivity";

    @Inject
    Gson gson;
    VaildateInfo vaildateInfo;
    @BindView(R.id.user_infoleft)
    TextView userInfoleft;
    @BindView(R.id.user_inforight)
    TextView userInforight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        DaggerOrderComponent.builder()
                .applicationComponent(MyApplication.GetInstance().getComponent())
                .commonNetParamComponent(DaggerCommonNetParamComponent.builder().commonNetParamModule(new CommonNetParamModule()).build())
                .build()
                .inject(this);
        Log.d(TAG, getIntent().getStringExtra("data"));
        vaildateInfo = gson.fromJson(getIntent().getStringExtra("data"), VaildateInfo.class);
        initUserInfo();
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

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
