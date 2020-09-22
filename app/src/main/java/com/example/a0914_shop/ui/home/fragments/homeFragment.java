package com.example.a0914_shop.ui.home.fragments;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a0914_shop.R;
import com.example.a0914_shop.adapters.HomeListAdapter;
import com.example.a0914_shop.base.BaseFragment;
import com.example.a0914_shop.bean.HomeBean;
import com.example.a0914_shop.interfaces.home.IHome;
import com.example.a0914_shop.presenter.home.HomePresenter;
import com.example.a0914_shop.ui.home.activitys.BrandDetailActivity;
import com.example.a0914_shop.ui.home.activitys.HotDetailActivity;
import com.example.a0914_shop.ui.home.activitys.TopicDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class homeFragment extends BaseFragment<IHome.IPresenter> implements IHome.IView {

    @BindView(R.id.rv)
    RecyclerView rv;
    private List<HomeBean.HomeListBean> list;
    private HomeListAdapter homeListAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        homeListAdapter = new HomeListAdapter(list, context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        homeListAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                int type = list.get(i).currentType;
                switch (type) {
                    case HomeBean.ITEM_TYPE_BANNER:
                    case HomeBean.ITEM_TYPE_TITLE:
                    case HomeBean.ITEM_TYPE_TITLETOP:
                    case HomeBean.ITEM_TYPE_TOPIC:
                    case HomeBean.ITEM_TYPE_HOT:
                        case HomeBean.ITEM_TYPE_TAB:
                        return 2;
                    case HomeBean.ITEM_TYPE_BRAND:
                    case HomeBean.ITEM_TYPE_NEW:
                    case HomeBean.ITEM_TYPE_CATEGORY:
                        return 1;
                }
                return 0;
            }
        });
        rv.setLayoutManager(gridLayoutManager);
        homeListAdapter.bindToRecyclerView(rv);
    }

    @Override
    protected IHome.IPresenter initPersenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        persenter.getHomeData();
    }

    @Override
    public void getHomeDataReturn(List<HomeBean.HomeListBean> result) {
        list.addAll(result);
        homeListAdapter.notifyDataSetChanged();
        homeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int type = list.get(position).currentType;
                Intent intent = new Intent();
                switch (type) {
                    case HomeBean.ITEM_TYPE_BANNER:

                        break;
                    case HomeBean.ITEM_TYPE_BRAND:
                        HomeBean.DataBean.BrandListBean brandListBean = (HomeBean.DataBean.BrandListBean) list.get(position).data;
                        intent.putExtra("id",brandListBean.getId());
                        intent.setClass(context, BrandDetailActivity.class);
                        startActivity(intent);
                        break;
                    case HomeBean.ITEM_TYPE_HOT:
                        HomeBean.DataBean.HotGoodsListBean listBean = (HomeBean.DataBean.HotGoodsListBean) list.get(position).data;
                        intent.putExtra("id",listBean.getId());
                        intent.setClass(context, HotDetailActivity.class);
                        startActivity(intent);
                        break;
                    case HomeBean.ITEM_TYPE_TITLE:

                        break;
                    case HomeBean.ITEM_TYPE_TITLETOP:

                        break;
                    case HomeBean.ITEM_TYPE_TOPIC:

                        break;

                    case HomeBean.ITEM_TYPE_CATEGORY:
                        HomeBean.DataBean.TopicListBean topicBean = (HomeBean.DataBean.TopicListBean) list.get(position).data;
                        intent.putExtra("id",topicBean.getId());
                        intent.setClass(context, TopicDetailActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
