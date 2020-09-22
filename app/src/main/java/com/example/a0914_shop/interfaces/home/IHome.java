package com.example.a0914_shop.interfaces.home;

import com.example.a0914_shop.bean.HomeBean;
import com.example.a0914_shop.interfaces.net.IBasePresenter;
import com.example.a0914_shop.interfaces.net.IBaseView;

import java.util.List;

public interface IHome {
    interface IView extends IBaseView {
        void getHomeDataReturn(List<HomeBean.HomeListBean> result);
    }

    interface IPresenter extends IBasePresenter<IView>{
        void getHomeData();
    }
}
