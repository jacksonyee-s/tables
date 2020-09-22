package com.example.a0914_shop.ui.home.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a0914_shop.R;
import com.example.a0914_shop.base.BaseAdapter;
import com.example.a0914_shop.bean.HomeBean;

import java.util.List;

public class TopicAdapter extends BaseAdapter {
    public TopicAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_topic;
    }

    @Override
    protected void bindData(BaseViewHolder baseViewHolder, Object data, int position) {
        HomeBean.DataBean.TopicListBean listBean = (HomeBean.DataBean.TopicListBean) data;
        if (!TextUtils.isEmpty(listBean.getItem_pic_url())){
            Glide.with(context).load(listBean.getItem_pic_url()).into((ImageView) baseViewHolder.getViewById(R.id.img_topic));
        }
        ((TextView)baseViewHolder.getViewById(R.id.tv_topic_name)).setText(listBean.getTitle());
        ((TextView)baseViewHolder.getViewById(R.id.tv_topic_content)).setText(listBean.getSubtitle());
    }
}
