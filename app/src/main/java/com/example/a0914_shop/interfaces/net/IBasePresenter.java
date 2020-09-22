package com.example.a0914_shop.interfaces.net;

public interface IBasePresenter<T extends IBaseView> {
    void attachView(T view);
    void detachView();

}
