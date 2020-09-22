package com.example.a0914_shop.presenter.cart;

import com.example.a0914_shop.base.BasePresenter;
import com.example.a0914_shop.bean.AdressBean;
import com.example.a0914_shop.common.CommonSubscriber;
import com.example.a0914_shop.interfaces.cart.ICart;
import com.example.a0914_shop.modle.HttpManager;
import com.example.a0914_shop.utils.RxUtils;


public class AdressPersenter extends BasePresenter<ICart.IAdressView> implements ICart.IAdressPersenter {
    @Override
    public void getAdressById(int parentId) {
        addSubscribe(HttpManager.getInstance().getApiService().getAdressById(parentId)
                .compose(RxUtils.<AdressBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<AdressBean>(mView) {
                    @Override
                    public void onNext(AdressBean result) {
                        mView.getAdressByIdReturn(result);
                    }
                }));
    }
}
