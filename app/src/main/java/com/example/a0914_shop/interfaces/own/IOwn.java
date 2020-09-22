package com.example.a0914_shop.interfaces.own;

import com.example.a0914_shop.bean.LoginBean;
import com.example.a0914_shop.interfaces.net.IBasePresenter;
import com.example.a0914_shop.interfaces.net.IBaseView;

import javax.crypto.spec.IvParameterSpec;

public interface IOwn {
    interface IView extends IBaseView{
        void getLoginReturn(LoginBean result);
    }
    interface IPresenter extends IBasePresenter<IView>{
        void getLogin(String username,String password);
    }
}
