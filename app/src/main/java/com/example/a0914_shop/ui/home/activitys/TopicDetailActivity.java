package com.example.a0914_shop.ui.home.activitys;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.a0914_shop.R;
import com.example.a0914_shop.base.BaseActivity;
import com.example.a0914_shop.bean.TopicDetailBean;
import com.example.a0914_shop.interfaces.home.ITopic;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicDetailActivity extends BaseActivity<ITopic.IPresenter> implements ITopic.IView {
    @BindView(R.id.web)
    WebView web;

    @Override
    protected void initData() {

    }

    @Override
    protected ITopic.IPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_topic_detail;
    }

    @Override
    public void getTopicDetailReturn(TopicDetailBean result) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
