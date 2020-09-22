package com.example.a0914_shop.interfaces.home;

import com.example.a0914_shop.bean.HomeBean;
import com.example.a0914_shop.bean.TopicBean;
import com.example.a0914_shop.bean.TopicDetailBean;
import com.example.a0914_shop.interfaces.net.IBasePresenter;
import com.example.a0914_shop.interfaces.net.IBaseView;

public interface ITopic {
    interface IView extends IBaseView{
        void getTopicDetailReturn(TopicDetailBean result);
    }
    interface IPresenter extends IBasePresenter<IView>{
        void getTopicDetail(int id);
    }
    interface ITopicView extends IBaseView{
        void getTopicReturn(TopicBean result);
    }
    interface ITopicPresenter extends IBasePresenter<ITopicView>{
        void getTopic();
    }
}
