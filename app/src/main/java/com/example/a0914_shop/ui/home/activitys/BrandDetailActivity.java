package com.example.a0914_shop.ui.home.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a0914_shop.R;
import com.example.a0914_shop.base.BaseActivity;
import com.example.a0914_shop.bean.BrandDetailBean;
import com.example.a0914_shop.interfaces.home.IBrand;
import com.example.a0914_shop.presenter.home.BrandPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandDetailActivity extends BaseActivity<IBrand.IPresenter> implements IBrand.IView {
    @BindView(R.id.img_brand_detail)
    ImageView imgBrandDetail;
    @BindView(R.id.tv_brand_detail_desc)
    TextView tvBrandDetailDesc;
    @BindView(R.id.img_back)
    ImageView imgBack;

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        mPresenter.getBrand(id);
    }

    @Override
    protected IBrand.IPresenter initPresenter() {
        return new BrandPresenter();
    }

    @Override
    protected void initView() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_brand_detail;
    }


    @Override
    public void getBrandDetailReturn(BrandDetailBean result) {
        Glide.with(this).load(result.getData().getBrand().getPic_url()).into(imgBrandDetail);
        tvBrandDetailDesc.setText(result.getData().getBrand().getSimple_desc());
    }


}
