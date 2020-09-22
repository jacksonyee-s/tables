package com.example.a0914_shop.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a0914_shop.R;
import com.example.a0914_shop.bean.HomeBean;
import com.example.a0914_shop.ui.home.adapters.TopicAdapter;
import com.example.a0914_shop.utils.SystemUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends BaseMultiItemQuickAdapter<HomeBean.HomeListBean, BaseViewHolder> implements LifecycleOwner {
    private Context context;
    private String priceword;
    private TopicAdapter topicAdapter;

    public HomeListAdapter(List<HomeBean.HomeListBean> data, Context context) {
        super(data);
        this.context = context;
        priceword = context.getString(R.string.word_price_brand);
        //ui绑定
        addItemType(HomeBean.ITEM_TYPE_BANNER,R.layout.layout_home_banner);
        addItemType(HomeBean.ITEM_TYPE_TAB,R.layout.layout_home_tab);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP,R.layout.layout_home_titletop);
        addItemType(HomeBean.ITEM_TYPE_BRAND,R.layout.layout_home_brand);
        addItemType(HomeBean.ITEM_TYPE_TITLE,R.layout.layout_home_title);
        addItemType(HomeBean.ITEM_TYPE_NEW,R.layout.layout_home_new);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP,R.layout.layout_home_titletop);
        addItemType(HomeBean.ITEM_TYPE_HOT,R.layout.layout_home_hot);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP,R.layout.layout_home_titletop);
        addItemType(HomeBean.ITEM_TYPE_TOPIC,R.layout.layout_home_topic);
        addItemType(HomeBean.ITEM_TYPE_CATEGORY,R.layout.layout_home_category);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(BaseViewHolder helper, HomeBean.HomeListBean item) {
        switch (item.getItemType()){
            case HomeBean.ITEM_TYPE_TITLE:
                updateTitle(helper,(String)item.data);
                break;
            case HomeBean.ITEM_TYPE_TITLETOP:
                updateTitle(helper,(String)item.data);
                break;
            case HomeBean.ITEM_TYPE_BANNER:
                updateBanner(helper,(List<HomeBean.DataBean.BannerBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_BRAND:
                updateBrand(helper,(HomeBean.DataBean.BrandListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_CATEGORY:
               updateCategory(helper,(HomeBean.DataBean.CategoryListBean.GoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_HOT:
                updateHot(helper,(HomeBean.DataBean.HotGoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_NEW:
                updateNew(helper,(HomeBean.DataBean.NewGoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_TOPIC:
                updateTopic(helper,(List<HomeBean.DataBean.TopicListBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_TAB:
                updateTab(helper,(List<HomeBean.DataBean.ChannelBean>) item.data);
                break;
        }
    }
//tab

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateTab(BaseViewHolder helper, List<HomeBean.DataBean.ChannelBean> data) {
        LinearLayout layouttabs = helper.getView(R.id.layout_tab);
        if (layouttabs.getChildCount()==0){
            for (HomeBean.DataBean.ChannelBean item:data) {
                Log.d(TAG, "updateTab: "+item.getName());
                String name = item.getName();
                TextView tabs = new TextView(context);
                tabs.setText(name);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                tabs.setLayoutParams(layoutParams);
                int px = SystemUtils.dp2px(context, 10);
                tabs.setTextSize(px);
                tabs.setGravity(Gravity.CENTER);
                Drawable icon = context.getDrawable(R.mipmap.ic_launcher);
                icon.setBounds(0,0,50,50);
                tabs.setCompoundDrawables(null,icon,null,null);
                layouttabs.addView(tabs);
            }
        }
    }

    //专题
    private void updateTopic(BaseViewHolder helper, List<HomeBean.DataBean.TopicListBean> data) {
        RecyclerView rvtopic = helper.getView(R.id.rv_topic);
        if (topicAdapter==null){
        topicAdapter = new TopicAdapter(context, data);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            rvtopic.setLayoutManager(linearLayoutManager);
        rvtopic.setAdapter(topicAdapter);
        }else if(rvtopic.getAdapter()==null){
            rvtopic.setAdapter(topicAdapter);
        }
    }

    //新品
    private void updateNew(BaseViewHolder helper,HomeBean.DataBean.NewGoodsListBean data) {
    if (!TextUtils.isEmpty(data.getList_pic_url())){
        Glide.with(context).load(data.getList_pic_url()).into((ImageView)helper.getView(R.id.new_img));
    }
    helper.setText(R.id.tv_new_name,data.getName());
        helper.setText(R.id.tv_new_price,"￥"+data.getRetail_price());
    }

    //人气推荐
    private void updateHot(BaseViewHolder helper, HomeBean.DataBean.HotGoodsListBean data) {
        if (!TextUtils.isEmpty(data.getList_pic_url())){
            Glide.with(context).load(data.getList_pic_url()).into((ImageView)helper.getView(R.id.img_hot));
        }
        helper.setText(R.id.tv_hot_name,data.getName());

        helper.setText(R.id.tv_hot_price,"￥"+data.getRetail_price());
    }

    //类别
    private void updateCategory(BaseViewHolder helper, HomeBean.DataBean.CategoryListBean.GoodsListBean data) {

        if (!TextUtils.isEmpty(data.getList_pic_url())){
            Glide.with(context).load(data.getList_pic_url()).into((ImageView)helper.getView(R.id.img_categroy));
        }
        helper.setText(R.id.tv_categroy_name,data.getName());
        helper.setText(R.id.tv_categroy_price,"￥"+data.getRetail_price());

    }

    //品牌
    private void updateBrand(BaseViewHolder helper, HomeBean.DataBean.BrandListBean data) {
        if (!TextUtils.isEmpty(data.getNew_pic_url())){
            Glide.with(context).load(data.getNew_pic_url()).into((ImageView) helper.getView(R.id.brand_img));
        }
        helper.setText(R.id.tv_brand_name,data.getName());
        helper.setText(R.id.tv_brand_price,"￥"+data.getFloor_price());
    }

    //banner
    private void updateBanner(BaseViewHolder helper, List<HomeBean.DataBean.BannerBean> data) {
        Banner banner = helper.getView(R.id.home_banner);
        if (banner.getTag()==null||(int)banner.getTag()==0){
            List<String> imgs = new ArrayList<>();
            for (HomeBean.DataBean.BannerBean item: data
                 ) {
                imgs.add(item.getImage_url());
            }
            banner.setTag(1);
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(imgs);
            banner.start();
        }
    }
//标题
    private void updateTitle(BaseViewHolder helper, String data) {
        helper.setText(R.id.tv_title,data);
    }

    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
