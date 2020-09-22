package com.example.a0914_shop.ui.special.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a0914_shop.R;
import com.example.a0914_shop.base.BaseAdapter;
import com.example.a0914_shop.bean.TopicBean;

import java.util.List;

import butterknife.BindView;

public class TopicAdapter extends BaseAdapter {

    public TopicAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_fragment_topic;
    }

    @Override
    protected void bindData(BaseViewHolder baseViewHolder, Object data, int position) {
        TopicBean.DataBeanX.DataBean list = (TopicBean.DataBeanX.DataBean) data;
        ImageView img = (ImageView) baseViewHolder.getViewById(R.id.img_topic);
        TextView tvtitle = (TextView) baseViewHolder.getViewById(R.id.tv_topic_title);
        TextView tvsubtitle = (TextView) baseViewHolder.getViewById(R.id.tv_topic_subtitle);
        TextView tvprice = (TextView) baseViewHolder.getViewById(R.id.tv_topic_price);
        Glide.with(context).load(list.getScene_pic_url()).into(img);
        tvtitle.setText(list.getTitle());
        tvsubtitle.setText(list.getSubtitle());
        tvprice.setText(list.getPrice_info()+"起");
    }
}
