package com.example.a0914_shop.presenter.cart;

import com.example.a0914_shop.base.BaseActivity;
import com.example.a0914_shop.base.BasePresenter;
import com.example.a0914_shop.bean.CartBean;
import com.example.a0914_shop.bean.DeleteCartBean;
import com.example.a0914_shop.common.CommonSubscriber;
import com.example.a0914_shop.interfaces.cart.ICart;
import com.example.a0914_shop.modle.HttpManager;
import com.example.a0914_shop.utils.RxUtils;

public class CartListPresenter extends BasePresenter<ICart.ICartView> implements ICart.ICartPersenter {
    @Override
    public void getCartList() {
        addSubscribe(HttpManager.getInstance().getApiService().getCartList()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<CartBean>(mView) {
            @Override
            public void onNext(CartBean cartBean) {
                mView.getCartListReturn(cartBean);
            }
        }));
    }

    @Override
    public void deleteCartList(String productIds) {
        addSubscribe(HttpManager.getInstance().getApiService().cartDelete(productIds)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCartBean>(mView) {
                    @Override
                    public void onNext(DeleteCartBean deleteCartBean) {
                        mView.deleteCartListReturn(deleteCartBean);
                    }
                }));
    }
}
