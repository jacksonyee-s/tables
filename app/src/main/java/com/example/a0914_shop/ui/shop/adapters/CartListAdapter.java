package com.example.a0914_shop.ui.shop.adapters;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.a0914_shop.R;
import com.example.a0914_shop.base.BaseAdapter;
import com.example.a0914_shop.bean.CartBean;
import com.example.a0914_shop.common.CartCustomView;

import java.util.List;

public class CartListAdapter extends BaseAdapter {

    public boolean isEditor;//编辑状态
    public CartListAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_shop;
    }

    @Override
    protected void bindData(BaseViewHolder baseViewHolder, Object data, int position) {
        CartBean.DataBean.CartListBean bean = (CartBean.DataBean.CartListBean) data;
        CheckBox check = (CheckBox) baseViewHolder.getViewById(R.id.checkbox_select);
        TextView tvname = (TextView) baseViewHolder.getViewById(R.id.txt_name);
        TextView tvprice = (TextView) baseViewHolder.getViewById(R.id.txt_price);
        TextView tvnumber = (TextView) baseViewHolder.getViewById(R.id.txt_number);
        TextView tvselect = (TextView) baseViewHolder.getViewById(R.id.txt_select);
        CartCustomView cartCustomView = (CartCustomView) baseViewHolder.getViewById(R.id.view_number);

        //通过编辑状态控制页面组件
        if (isEditor){
            tvname.setVisibility(View.GONE);
            tvnumber.setVisibility(View.GONE);
            tvselect.setVisibility(View.VISIBLE);
            cartCustomView.setVisibility(View.VISIBLE);
        }else {
            tvname.setVisibility(View.VISIBLE);
            tvnumber.setVisibility(View.VISIBLE);
            tvselect.setVisibility(View.GONE);
            cartCustomView.setVisibility(View.GONE);
        }
        tvname.setText(bean.getGoods_name());
        tvnumber.setText("X"+bean.getNumber());
        tvprice.setText("￥"+bean.getRetail_price());
        cartCustomView.initView();
        cartCustomView.setValue((int) bean.getNumber());
        cartCustomView.setOnClick(new CartCustomView.OnClick() {
            @Override
            public void onclick(int value) {
                bean.setNumber(value);
            }
        });
        check.setSelected(bean.select);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bean.select = b;
                if (checkClick!=null){
                    checkClick.onclick();
                }
            }
        });

    }
    private checkClick checkClick;

    public void setCheckClick(CartListAdapter.checkClick checkClick) {
        this.checkClick = checkClick;
    }

    public interface checkClick{
        void onclick();
    }
}
