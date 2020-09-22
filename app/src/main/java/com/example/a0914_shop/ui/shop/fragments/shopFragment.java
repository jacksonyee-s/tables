package com.example.a0914_shop.ui.shop.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a0914_shop.R;
import com.example.a0914_shop.base.BaseFragment;
import com.example.a0914_shop.bean.CartBean;
import com.example.a0914_shop.bean.DeleteCartBean;
import com.example.a0914_shop.interfaces.cart.ICart;
import com.example.a0914_shop.presenter.cart.CartListPresenter;
import com.example.a0914_shop.ui.shop.activitys.AddressActivity;
import com.example.a0914_shop.ui.shop.adapters.CartListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class shopFragment extends BaseFragment<ICart.ICartPersenter> implements ICart.ICartView {
    @BindView(R.id.layout_top)
    FrameLayout layoutTop;
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    @BindView(R.id.rb_select)
    CheckBox rbSelect;
    @BindView(R.id.tv_allprice)
    TextView tvAllprice;
    @BindView(R.id.tv_shop_buy)
    TextView tvShopBuy;
    @BindView(R.id.rl_shop)
    ConstraintLayout rlShop;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_allselect)
    TextView tvAllselect;
    private ArrayList<CartBean.DataBean.CartListBean> list;
    private CartListAdapter cartListAdapter;
    private int allNumber;
    private int allPrice;

    @Override
    protected int getLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        cartListAdapter = new CartListAdapter(context, list);
        rvShop.setLayoutManager(new LinearLayoutManager(context));
        rvShop.setAdapter(cartListAdapter);
        tvAllselect.setText("全选（0）");
        cartListAdapter.setCheckClick(new CartListAdapter.checkClick() {
            @Override
            public void onclick() {
                //判断当前是否全选
                boolean boo = CheckSelectAll();
                tvAllprice.setText("全选(" + allNumber + ")");
                tvAllprice.setText("￥" + allPrice);
                rbSelect.setSelected(boo);
                cartListAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 全选状态的切换
     */
    private void selectAll() {
        //设置当前是否是权限
        resetSelect(!rbSelect.isChecked());
        rbSelect.setSelected(!rbSelect.isChecked());
        tvAllselect.setText("全选(" + allNumber + ")");
        tvAllprice.setText("￥" + allPrice);
        cartListAdapter.notifyDataSetChanged();
    }

    /**
     * 编辑状态的切换
     */
    private void clickEdit() {
        //当前是默认的状态---编辑状态
        if ("编辑".equals(tvEdit.getText())) {
            cartListAdapter.isEditor = true;
            tvEdit.setText("完成");
            tvShopBuy.setText("删除所选");
            tvAllprice.setVisibility(View.GONE);
        } else if ("完成".equals(tvEdit.getText())) {   //编辑状态进入默认状态
            cartListAdapter.isEditor = false;
            tvEdit.setText("编辑");
            tvShopBuy.setText("下单");
            tvAllprice.setVisibility(View.VISIBLE);
            tvAllprice.setText("￥0");
        }
        resetSelect(false);
        cartListAdapter.notifyDataSetChanged();
    }

    /**
     * 提交
     */
    private void submitData() {
        if ("下单".equals(tvShopBuy.getText())) {
            //提交数据
            //先跳转到地址页面吧
            Intent intent = new Intent(context, AddressActivity.class);
            startActivity(intent);

        } else if ("删除所选".equals(tvShopBuy.getText())) {
            StringBuilder sb = new StringBuilder();
            List<Integer> ids = getSelectProducts();
            for (Integer id : ids) {
                sb.append(id);
                sb.append(",");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
                String productIds = sb.toString();
                persenter.deleteCartList(productIds);
            } else {
                Toast.makeText(context, "没有选中要删除的商品", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 重置选中状态
     *
     * @param bool
     */
    private void resetSelect(boolean bool) {
        allNumber = 0;
        allPrice = 0;
        for (CartBean.DataBean.CartListBean item : list) {
            item.select = bool;
            if (bool) {
                allNumber += item.getNumber();
                allPrice += item.getNumber() * item.getRetail_price();
            }
        }
        if (!bool) {
            allNumber = 0;
            allPrice = 0;
        }
    }

    /**
     * 获取当前选中的商品
     *
     * @return
     */
    private List<Integer> getSelectProducts() {
        List<Integer> ids = new ArrayList<>();
        for (CartBean.DataBean.CartListBean item : list) {
            if (item.select) {
                ids.add(item.getProduct_id());
            }
        }
        return ids;
    }

    @Override
    protected ICart.ICartPersenter initPersenter() {
        return new CartListPresenter();
    }

    @Override
    protected void initData() {
        persenter.getCartList();
    }


    @Override
    public void getCartListReturn(CartBean result) {
        list.addAll(result.getData().getCartList());
        cartListAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.rb_select, R.id.tv_edit, R.id.tv_shop_buy})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rb_select:
                selectAll();
                break;
            case R.id.tv_edit:
                clickEdit();
                break;
            case R.id.tv_shop_buy:
                submitData();
                break;
        }
    }

    @Override
    public void deleteCartListReturn(DeleteCartBean result) {

    }

    /**
     * 判断当前数据是否选中
     *
     * @return
     */
    private boolean CheckSelectAll() {
        allPrice = 0;
        allNumber = 0;
        boolean isSelectAll = true;
        for (CartBean.DataBean.CartListBean item : list) {
            if (item.select) {
                allNumber += item.getNumber();
                allPrice += item.getNumber() * item.getRetail_price();
            }
            if (isSelectAll && !item.select) {
                isSelectAll = false;
            }
        }
        return isSelectAll;
    }
}
