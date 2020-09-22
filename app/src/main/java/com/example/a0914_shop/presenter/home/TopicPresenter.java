package com.example.a0914_shop.presenter.home;

import com.example.a0914_shop.base.BasePresenter;
import com.example.a0914_shop.bean.TopicDetailBean;
import com.example.a0914_shop.common.CommonSubscriber;
import com.example.a0914_shop.interfaces.home.ITopic;
import com.example.a0914_shop.modle.HttpManager;
import com.example.a0914_shop.utils.RxUtils;

public class TopicPresenter extends BasePresenter<ITopic.IView> implements ITopic.IPresenter{

    @Override
    public void getTopicDetail(int id) {
        addSubscribe(HttpManager.getInstance().getApiService().getTopicDetail(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TopicDetailBean>(mView) {
            @Override
            public void onNext(TopicDetailBean topicDetailBean) {
                mView.getTopicDetailReturn(topicDetailBean);
            }
        }));
    }
}