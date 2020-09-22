package com.example.a0914_shop.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a0914_shop.interfaces.net.IBasePresenter;
import com.example.a0914_shop.interfaces.net.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {
    private Unbinder bind;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        initView();
        mPresenter = initPresenter();
        if (mPresenter!=null){
            mPresenter.attachView(this);
            initData();
        }
    }

    protected abstract void initData();

    protected abstract P initPresenter();


    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            bind.unbind();
        }
        if (mPresenter!=null){
            mPresenter.detachView();
        }

    }
}
