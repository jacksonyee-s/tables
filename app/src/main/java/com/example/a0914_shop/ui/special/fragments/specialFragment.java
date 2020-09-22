package com.example.a0914_shop.ui.special.fragments;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a0914_shop.R;
import com.example.a0914_shop.base.BaseFragment;
import com.example.a0914_shop.bean.TopicBean;
import com.example.a0914_shop.interfaces.home.ITopic;
import com.example.a0914_shop.interfaces.net.IBasePresenter;
import com.example.a0914_shop.presenter.topic.TopicFragmentPresenter;
import com.example.a0914_shop.ui.special.adapters.TopicAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class specialFragment extends BaseFragment<ITopic.ITopicPresenter> implements ITopic.ITopicView {
    @BindView(R.id.rv_topic)
    RecyclerView rvTopic;
    private List<TopicBean.DataBeanX.DataBean> list;
    private TopicAdapter topicAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_special;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        topicAdapter = new TopicAdapter(context, list);
        rvTopic.setLayoutManager(new LinearLayoutManager(context));
        rvTopic.setAdapter(topicAdapter);
    }

    @Override
    protected ITopic.ITopicPresenter initPersenter() {
        return new TopicFragmentPresenter();
    }

    @Override
    protected void initData() {
        persenter.getTopic();
    }

    @Override
    public void getTopicReturn(TopicBean result) {
        list.addAll(result.getData().getData());
        Log.d(TAG, "getTopicReturn: "+list.size());
        topicAdapter.notifyDataSetChanged();
    }

    private static final String TAG = "specialFragment";
}
