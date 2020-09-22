package com.example.a0914_shop.presenter.topic;

import com.example.a0914_shop.base.BasePresenter;
import com.example.a0914_shop.bean.TopicBean;
import com.example.a0914_shop.common.CommonSubscriber;
import com.example.a0914_shop.interfaces.home.ITopic;
import com.example.a0914_shop.modle.HttpManager;
import com.example.a0914_shop.utils.RxUtils;

public class TopicFragmentPresenter extends BasePresenter<ITopic.ITopicView> implements ITopic.ITopicPresenter {
    @Override
    public void getTopic() {
        addSubscribe(HttpManager.getInstance().getApiService().getTopic()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TopicBean>(mView) {
            @Override
            public void onNext(TopicBean topicBean) {
                mView.getTopicReturn(topicBean);
            }
        }));
    }
}
