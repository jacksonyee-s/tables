package com.example.a0914_shop.presenter.own;

import com.example.a0914_shop.base.BasePresenter;
import com.example.a0914_shop.bean.LoginBean;
import com.example.a0914_shop.common.CommonSubscriber;
import com.example.a0914_shop.interfaces.own.IOwn;
import com.example.a0914_shop.modle.HttpManager;
import com.example.a0914_shop.utils.RxUtils;

public class LoginPresenter extends BasePresenter<IOwn.IView> implements IOwn.IPresenter {
    @Override
    public void getLogin(String username, String password) {
        addSubscribe(HttpManager.getInstance().getApiService().getLogin(username,password)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<LoginBean>(mView) {
            @Override
            public void onNext(LoginBean loginBean) {
                mView.getLoginReturn(loginBean);
            }
        }));
    }
}
