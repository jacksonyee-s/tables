package com.example.a0914_shop.presenter.home;

import com.example.a0914_shop.base.BasePresenter;
import com.example.a0914_shop.bean.BrandDetailBean;
import com.example.a0914_shop.common.CommonSubscriber;
import com.example.a0914_shop.interfaces.home.IBrand;
import com.example.a0914_shop.modle.HttpManager;
import com.example.a0914_shop.utils.RxUtils;

public class BrandPresenter extends BasePresenter<IBrand.IView> implements IBrand.IPresenter {
    @Override
    public void getBrand(int id) {
        addSubscribe(HttpManager.getInstance().getApiService().getBrandDetail(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<BrandDetailBean>(mView) {
            @Override
            public void onNext(BrandDetailBean brandDetailBean) {
                mView.getBrandDetailReturn(brandDetailBean);
            }
        }));
    }
}
