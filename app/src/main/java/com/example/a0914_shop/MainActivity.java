package com.example.a0914_shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.a0914_shop.base.BaseActivity;
import com.example.a0914_shop.interfaces.net.IBasePresenter;
import com.example.a0914_shop.ui.classify.fragments.classifyFragment;
import com.example.a0914_shop.ui.home.fragments.homeFragment;
import com.example.a0914_shop.ui.own.fragments.ownFragment;
import com.example.a0914_shop.ui.shop.fragments.shopFragment;
import com.example.a0914_shop.ui.special.fragments.specialFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.tab_main)
    TabLayout tabMain;
    private FragmentManager fragmentManager;
    private com.example.a0914_shop.ui.home.fragments.homeFragment homeFragment;
    private com.example.a0914_shop.ui.special.fragments.specialFragment specialFragment;
    private com.example.a0914_shop.ui.classify.fragments.classifyFragment classifyFragment;
    private com.example.a0914_shop.ui.own.fragments.ownFragment ownFragment;
    private com.example.a0914_shop.ui.shop.fragments.shopFragment shopFragment;


    @Override
    protected void initData() {
        fragmentManager.beginTransaction()
                .add(R.id.ll_main,homeFragment)
                .show(homeFragment)
                .commit();
    }

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        fragmentManager = getSupportFragmentManager();
        homeFragment = new homeFragment();
        specialFragment = new specialFragment();
        classifyFragment = new classifyFragment();
        ownFragment = new ownFragment();
        shopFragment = new shopFragment();
        tabMain.addTab(tabMain.newTab().setText("首页").setIcon(R.drawable.select_one),0);
        tabMain.addTab(tabMain.newTab().setText("专题").setIcon(R.drawable.select_two),1);
        tabMain.addTab(tabMain.newTab().setText("分类").setIcon(R.drawable.select_three),2);
        tabMain.addTab(tabMain.newTab().setText("购物车").setIcon(R.drawable.select_four),3);
        tabMain.addTab(tabMain.newTab().setText("我的").setIcon(R.drawable.select_five),4);
        initData();
        tabMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ininFragment(tab.getPosition());
            }



            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    private void ininFragment(int position) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (position){
            case 0:
                if (!homeFragment.isAdded()) {
                    fragmentTransaction.add(R.id.ll_main, homeFragment).hide(classifyFragment).hide(specialFragment).hide(ownFragment).hide(shopFragment);
                }
                fragmentTransaction.show(homeFragment).hide(classifyFragment).hide(specialFragment).hide(ownFragment).hide(shopFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                if (!specialFragment.isAdded()) {
                    fragmentTransaction.add(R.id.ll_main, specialFragment).hide(homeFragment).hide(classifyFragment).hide(ownFragment).hide(shopFragment);
                }
                fragmentTransaction.show(specialFragment).hide(homeFragment).hide(classifyFragment).hide(ownFragment).hide(shopFragment);
                fragmentTransaction.commit();
                break;

            case 2:
                if (!classifyFragment.isAdded()) {
                    fragmentTransaction.add(R.id.ll_main, classifyFragment).hide(homeFragment).hide(specialFragment).hide(ownFragment).hide(shopFragment);
                }
                fragmentTransaction.show(classifyFragment).hide(homeFragment).hide(specialFragment).hide(ownFragment).hide(shopFragment);
                fragmentTransaction.commit();
                break;
            case 3:
                if (!shopFragment.isAdded()) {
                    fragmentTransaction.add(R.id.ll_main, shopFragment).hide(homeFragment).hide(classifyFragment).hide(specialFragment).hide(ownFragment);
                }
                fragmentTransaction.show(shopFragment).hide(homeFragment).hide(classifyFragment).hide(specialFragment).hide(ownFragment);
                fragmentTransaction.commit();
                break;
            case 4:
                if (!ownFragment.isAdded()) {
                    fragmentTransaction.add(R.id.ll_main, ownFragment).hide(homeFragment).hide(classifyFragment).hide(specialFragment).hide(shopFragment);
                }
                fragmentTransaction.show(ownFragment).hide(homeFragment).hide(classifyFragment).hide(specialFragment).hide(shopFragment);
                fragmentTransaction.commit();
                break;
        }
    }
    /**
     * 跳转购物车的切换
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}