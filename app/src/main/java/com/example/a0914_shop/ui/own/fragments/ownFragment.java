package com.example.a0914_shop.ui.own.fragments;

import android.content.Intent;
import android.service.quickaccesswallet.GetWalletCardsCallback;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.a0914_shop.R;
import com.example.a0914_shop.base.BaseFragment;
import com.example.a0914_shop.interfaces.net.IBasePresenter;
import com.example.a0914_shop.ui.own.activitys.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ownFragment extends BaseFragment {
    @BindView(R.id.img_own)
    ImageView imgOwn;
    @BindView(R.id.img_own_go)
    ImageView imgOwnGo;
    @BindView(R.id.tv_own)
    TextView tvOwn;
    @BindView(R.id.cl_own)
    ConstraintLayout clOwn;

    @Override
    protected int getLayout() {
        return R.layout.fragment_own;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected IBasePresenter initPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.img_own, R.id.img_own_go, R.id.tv_own})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_own:
                //点击弹出 选择登陆方式
                showLogin();
                break;
            case R.id.img_own_go:
                //点击弹出 选择登陆方式
                showLogin();
                break;
            case R.id.tv_own:
                //点击弹出 选择登陆方式
                showLogin();
                break;
        }
    }

    private void Register() {
        Intent intent = new Intent(context, RegisterActivity.class);
        startActivity(intent);
    }

    private void showLogin() {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_login, null);
        PopupWindow popupWindow = new PopupWindow(inflate, 400, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        background(0.5f);
        popupWindow.showAtLocation(clOwn, Gravity.CENTER,0,0);
        TextView tvregister = inflate.findViewById(R.id.txt_register);
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转到注册页面
                Register();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                background(1f);
            }
        });
    }

    private void background(float v) {
        WindowManager.LayoutParams lp =getActivity().getWindow().getAttributes();
        lp.alpha = v;
        getActivity().getWindow().setAttributes(lp);

    }
}
