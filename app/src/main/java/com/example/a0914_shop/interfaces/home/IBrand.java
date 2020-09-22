package com.example.a0914_shop.interfaces.home;

import com.example.a0914_shop.bean.BrandDetailBean;
import com.example.a0914_shop.interfaces.net.IBasePresenter;
import com.example.a0914_shop.interfaces.net.IBaseView;

public interface IBrand {
    interface IView extends IBaseView{
        void getBrandDetailReturn(BrandDetailBean result);
    }
    interface IPresenter extends IBasePresenter<IView>{
        void getBrand(int id);
    }
}
