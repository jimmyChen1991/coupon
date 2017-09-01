package com.hhyg.TyClosing.ui.fragment.order;

import android.view.View;

import com.hhyg.TyClosing.R;
import com.hhyg.TyClosing.ui.fragment.BaseBottomDialogFragment;


/**
 * Created by user on 2017/8/28.
 */

public class BounsFragment extends BaseBottomDialogFragment{
    @Override
    public int getLayoutRes() {
        return R.layout.dialog_bouns;
    }

    @Override
    public void bindView(View v) {

    }

    @Override
    public int getHeight() {
        return 1000;
    }

    @Override
    public float getDimAmount() {
        return (float) 0.5;
    }

    @Override
    public boolean getCancelOutside() {
        return false;
    }
}
