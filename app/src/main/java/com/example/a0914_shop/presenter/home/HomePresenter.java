package com.example.a0914_shop.presenter.home;


import com.example.a0914_shop.base.BasePresenter;
import com.example.a0914_shop.bean.HomeBean;
import com.example.a0914_shop.common.CommonSubscriber;
import com.example.a0914_shop.interfaces.home.IHome;
import com.example.a0914_shop.modle.HttpManager;
import com.example.a0914_shop.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


public class HomePresenter extends BasePresenter<IHome.IView> implements IHome.IPresenter {
    @Override
    public void getHomeData() {
        addSubscribe((Disposable) HttpManager.getInstance().getApiService().getHomeContent()
        .compose(RxUtils.<HomeBean>rxScheduler())
        .map(new Function<HomeBean, List<HomeBean.HomeListBean>>() {
            @Override
            public List<HomeBean.HomeListBean> apply(HomeBean homeBean) throws Exception {
                List<HomeBean.HomeListBean> list = new ArrayList<>();
                //第一个对象的封装 Banner
                HomeBean.HomeListBean banner = new HomeBean.HomeListBean();
                banner.currentType = HomeBean.ITEM_TYPE_BANNER;
                banner.data = homeBean.getData().getBanner();
                list.add(banner);
                //导航的封装
                HomeBean.HomeListBean tab = new HomeBean.HomeListBean();
                tab.currentType = HomeBean.ITEM_TYPE_TAB;
                tab.data = homeBean.getData().getChannel();
                list.add(tab);
                //封装带top边距的标题
                HomeBean.HomeListBean title1 = new HomeBean.HomeListBean();
                title1.currentType = HomeBean.ITEM_TYPE_TITLETOP;
                title1.data = "品牌制造商直供";
                list.add(title1);
                //封装品牌制造商直供的列表数据
                for (int i=0; i<homeBean.getData().getBrandList().size(); i++){
                    HomeBean.HomeListBean brand = new HomeBean.HomeListBean();
                    brand.currentType = HomeBean.ITEM_TYPE_BRAND;
                    brand.data = homeBean.getData().getBrandList().get(i);
                    list.add(brand);
                }
                //新品首发标题
                HomeBean.HomeListBean title2 = new HomeBean.HomeListBean();
                title2.currentType = HomeBean.ITEM_TYPE_TITLE;
                title2.data = "周一周四·新品首发";
                list.add(title2);
                //新品首发数据封装
                for (int i=0; i<homeBean.getData().getNewGoodsList().size(); i++){
                    HomeBean.HomeListBean brand = new HomeBean.HomeListBean();
                    brand.currentType = HomeBean.ITEM_TYPE_NEW;
                    brand.data = homeBean.getData().getNewGoodsList().get(i);
                    list.add(brand);
                }
                //人气推荐
                HomeBean.HomeListBean title3 = new HomeBean.HomeListBean();
                title3.currentType = HomeBean.ITEM_TYPE_TITLETOP;
                title3.data = "人气推荐";
                list.add(title3);
                //人气推荐数据
                for (int i=0; i<homeBean.getData().getHotGoodsList().size(); i++){
                    HomeBean.HomeListBean brand = new HomeBean.HomeListBean();
                    brand.currentType = HomeBean.ITEM_TYPE_HOT;
                    brand.data = homeBean.getData().getHotGoodsList().get(i);
                    list.add(brand);
                }
                //专题精选
                HomeBean.HomeListBean title4 = new HomeBean.HomeListBean();
                title4.currentType = HomeBean.ITEM_TYPE_TITLETOP;
                title4.data = "专题精选";
                list.add(title4);
                //专题精选数据
                HomeBean.HomeListBean brand = new HomeBean.HomeListBean();
                brand.currentType = HomeBean.ITEM_TYPE_TOPIC;
                brand.data = homeBean.getData().getTopicList();
                list.add(brand);
                //居家
                for (int i = 0; i <homeBean.getData().getCategoryList().size() ; i++) {
                    HomeBean.HomeListBean title5 = new HomeBean.HomeListBean();
                    title5.currentType = HomeBean.ITEM_TYPE_TITLETOP;
                    title5.data = homeBean.getData().getCategoryList().get(i).getName();
                    list.add(title5);
                    //居家数据
                    for (int j = 0; j <homeBean.getData().getCategoryList().get(i).getGoodsList().size() ; j++) {
                        HomeBean.HomeListBean inhome = new HomeBean.HomeListBean();
                        inhome.currentType = HomeBean.ITEM_TYPE_CATEGORY;
                        inhome.data = homeBean.getData().getCategoryList().get(i).getGoodsList().get(j);
                        list.add(inhome);
                    }
                }


                return list;


            }
        })
        .subscribeWith(new CommonSubscriber<List<HomeBean.HomeListBean>>(mView) {

            @Override
            public void onNext(List<HomeBean.HomeListBean> homeListBeans) {
                mView.getHomeDataReturn(homeListBeans);
            }
        }));
    }
}
