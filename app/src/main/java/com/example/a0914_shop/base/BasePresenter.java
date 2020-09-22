package com.example.a0914_shop.base;


import com.example.a0914_shop.interfaces.net.IBasePresenter;
import com.example.a0914_shop.interfaces.net.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    protected V mView;
    WeakReference<V> weakReference;
    //rxjava2 背压
    CompositeDisposable compositeDisposable;

    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        mView = weakReference.get();
    }
    public void addSubscribe(Disposable disposable){
        if (compositeDisposable==null) compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);

    }
    public void clearSubscribe(){
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }
    @Override
    public void detachView() {
        mView=null;
        clearSubscribe();
    }
}
