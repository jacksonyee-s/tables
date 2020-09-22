package com.example.a0914_shop.interfaces.cart;

import com.example.a0914_shop.bean.AddCartInfoBean;
import com.example.a0914_shop.bean.AdressBean;
import com.example.a0914_shop.bean.CartBean;
import com.example.a0914_shop.bean.DeleteCartBean;
import com.example.a0914_shop.bean.GoodDetailBean;
import com.example.a0914_shop.bean.HomeBean;
import com.example.a0914_shop.interfaces.net.IBasePresenter;
import com.example.a0914_shop.interfaces.net.IBaseView;

public interface ICart {
    interface IView extends IBaseView{
        void getGoodDetailReturn(GoodDetailBean result);
        //添加商品信息返回
        void addCartReturn(AddCartInfoBean result);
    }
    interface Ipresenter extends IBasePresenter<IView>{
        void getGoodList(int id);
        //添加商品到购物车
        void addCart(int goodsId,int number,int productId);
    }
    interface ICartView extends IBaseView{
        void getCartListReturn(CartBean result);

        void deleteCartListReturn(DeleteCartBean result);
    }

    interface ICartPersenter extends IBasePresenter<ICartView>{

        //获取购物车的数据
        void getCartList();

        //删除购物车数据
        void deleteCartList(String productIds);

    }
    interface IAdressView extends IBaseView{
        void getAdressByIdReturn(AdressBean result);
    }

    interface IAdressPersenter extends IBasePresenter<IAdressView>{
        void getAdressById(int parentId);
    }
}
