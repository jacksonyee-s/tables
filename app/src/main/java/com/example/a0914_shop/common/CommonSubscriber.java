package com.example.a0914_shop.common;

import android.text.TextUtils;

import com.example.a0914_shop.interfaces.net.IBaseView;

import io.reactivex.subscribers.ResourceSubscriber;


public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private IBaseView mView;
    private String errorMsg;
    private boolean isShowErrorState = false;
    protected CommonSubscriber(IBaseView view){
        mView = view;
    }
    protected CommonSubscriber(IBaseView view, boolean isError){
        mView = view;
        isShowErrorState = isError;
    }
    protected CommonSubscriber(IBaseView view, String emsg, boolean isError){
        mView = view;
        errorMsg = emsg;
        isShowErrorState = isError;
    }


    @Override
    public void onError(Throwable t) {
        if (mView==null)return;
        if (errorMsg!=null && TextUtils.isEmpty(errorMsg)){
            mView.showTips(errorMsg);
        }
    }

    @Override
    public void onComplete() {

    }
}