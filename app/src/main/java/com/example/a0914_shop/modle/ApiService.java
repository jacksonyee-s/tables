package com.example.a0914_shop.modle;



import androidx.constraintlayout.helper.widget.Flow;

import com.example.a0914_shop.bean.AddCartInfoBean;
import com.example.a0914_shop.bean.AdressBean;
import com.example.a0914_shop.bean.BrandDetailBean;
import com.example.a0914_shop.bean.CartBean;
import com.example.a0914_shop.bean.DeleteCartBean;
import com.example.a0914_shop.bean.GoodDetailBean;
import com.example.a0914_shop.bean.HomeBean;
import com.example.a0914_shop.bean.LoginBean;
import com.example.a0914_shop.bean.TopicBean;
import com.example.a0914_shop.bean.TopicDetailBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    @GET("index")
    Flowable<HomeBean> getHomeContent();
    //人气推荐点击进入详情
    @GET("goods/detail")
    Flowable<GoodDetailBean> getGoodDetail(@Query("id") int id);
    //https://cdwan.cn/api/brand/detail?id=1001000
    //制造商点击进入详情
    @GET("brand/detail")
    Flowable<BrandDetailBean> getBrandDetail(@Query("id")int id);
    //专题详情页面
    @GET("topic/detail")
    Flowable<TopicDetailBean> getTopicDetail(@Query("id")int id);

    //添加到购物车
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCartInfoBean> addCart(@Field("goodsId") int goodsId, @Field("number") int number, @Field("productId") int productId);

    @GET("cart/index")
    Flowable<CartBean> getCartList();

    //删除购物车
    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCartBean> cartDelete(@Field("productIds") String productIds);

    //登录
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> getLogin(@Field("username") String username,@Field("password") String password);

    //地址
    @GET("region/list")
    Flowable<AdressBean> getAdressById(@Query("parentId") int parentId);

    //专题Fragment
    @GET("topic/list")
    Flowable<TopicBean> getTopic();
}
