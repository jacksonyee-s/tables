package com.example.a0914_shop.ui.own.activitys;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.a0914_shop.R;
import com.example.a0914_shop.base.BaseActivity;
import com.example.a0914_shop.bean.LoginBean;
import com.example.a0914_shop.interfaces.own.IOwn;
import com.example.a0914_shop.presenter.own.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity<IOwn.IPresenter> implements IOwn.IView {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_psd)
    EditText etPsd;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void initData() {

    }

    @Override
    protected IOwn.IPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        String name = etName.getText().toString().trim();
        String psd = etPsd.getText().toString().trim();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void getLoginReturn(LoginBean result) {

    }


}
