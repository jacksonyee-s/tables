package com.example.a0914_shop.presenter.cart;

import com.example.a0914_shop.base.BasePresenter;
import com.example.a0914_shop.bean.AddCartInfoBean;
import com.example.a0914_shop.bean.GoodDetailBean;
import com.example.a0914_shop.common.CommonSubscriber;
import com.example.a0914_shop.interfaces.cart.ICart;
import com.example.a0914_shop.modle.HttpManager;
import com.example.a0914_shop.utils.RxUtils;

public class CartPresenter extends BasePresenter<ICart.IView> implements ICart.Ipresenter {
    @Override
    public void getGoodList(int id) {
        addSubscribe(HttpManager.getInstance().getApiService().getGoodDetail(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<GoodDetailBean>(mView) {
            @Override
            public void onNext(GoodDetailBean goodDetailBean) {
                mView.getGoodDetailReturn(goodDetailBean);
            }
        }));
    }

    @Override
    public void addCart(int goodsId, int number, int productId) {
        addSubscribe(HttpManager.getInstance().getApiService().addCart(goodsId,number,productId)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCartInfoBean>(mView) {
                    @Override
                    public void onNext(AddCartInfoBean addCartInfoBean) {
                        mView.addCartReturn(addCartInfoBean);
                    }
                }));
    }
}
