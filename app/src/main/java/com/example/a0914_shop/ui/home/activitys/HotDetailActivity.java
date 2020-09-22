package com.example.a0914_shop.ui.home.activitys;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a0914_shop.R;
import com.example.a0914_shop.base.BaseActivity;
import com.example.a0914_shop.bean.AddCartInfoBean;
import com.example.a0914_shop.bean.GoodDetailBean;
import com.example.a0914_shop.common.CartCustomView;
import com.example.a0914_shop.interfaces.cart.ICart;
import com.example.a0914_shop.presenter.cart.CartPresenter;
import com.example.a0914_shop.utils.SystemUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotDetailActivity extends BaseActivity<ICart.Ipresenter> implements ICart.IView {
    @BindView(R.id.good_banner)
    Banner goodBanner;
    @BindView(R.id.good_name)
    TextView goodName;
    @BindView(R.id.good_content)
    TextView goodContent;
    @BindView(R.id.good_price)
    TextView goodPrice;
    @BindView(R.id.good_boss)
    TextView goodBoss;
    @BindView(R.id.good_img_go)
    ImageView goodImgGo;
    @BindView(R.id.fragment_nors)
    FrameLayout fragmentNors;
    @BindView(R.id.good_img_go_comment)
    ImageView goodImgGoComment;
    @BindView(R.id.fragment_comment)
    FrameLayout fragmentComment;
    @BindView(R.id.good_comment_time)
    TextView goodCommentTime;
    @BindView(R.id.good_comment_conent)
    TextView goodCommentConent;
    @BindView(R.id.comment_imgs)
    LinearLayout commentImgs;
    @BindView(R.id.comment_parameter)
    LinearLayout commentParameter;
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.rv_all_look)
    RecyclerView rvAllLook;
    @BindView(R.id.layout_collect)
    RelativeLayout layoutCollect;
    @BindView(R.id.img_cart)
    ImageView imgCart;
    @BindView(R.id.layout_cart)
    RelativeLayout layoutCart;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.ll_question)
    LinearLayout llQuestion;
    @BindView(R.id.ll_down)
    LinearLayout llDown;
    @BindView(R.id.tv_comment_size)
    TextView tvCommentSize;
    @BindView(R.id.tv_joincart)
    TextView tvJoincart;
    @BindView(R.id.shop_count)
    TextView shopcount;

    private String html = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                $\n" +
            "            </body>\n" +
            "        </html>";
    private PopupWindow popupWindow;
    private int currentNum = 1;
    private GoodDetailBean goodDetailBean;

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        mPresenter.getGoodList(id);
    }

    @Override
    protected ICart.Ipresenter initPresenter() {
        return new CartPresenter();
    }

    @Override
    protected void initView() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //点击出现弹窗
        fragmentNors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopwindow();
            }
        });
        //点击收藏

        //点击购物车
        layoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(1000);
                finish();
            }
        });

        //点击加入购物车
        tvJoincart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCart();
            }
        });

    }

    private void addCart() {

        //判断当前弹窗是否打开了
        //打开了
        if (popupWindow != null && popupWindow.isShowing()) {
            //添加到购物车操作
            if (goodDetailBean.getData().getProductList().size() > 0) {
                int goodsId = goodDetailBean.getData().getProductList().get(0).getGoods_id();
                int productid = goodDetailBean.getData().getProductList().get(0).getId();
                mPresenter.addCart(goodsId, currentNum, productid);
                popupWindow.dismiss();
                popupWindow = null;
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        } else {
            //打开弹窗
            showPopwindow();
        }
    }

    //弹窗
    private void showPopwindow() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_detail_popwindow, null);
        int height = SystemUtils.dp2px(this, 250);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, height);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(false);
        popupWindow.setContentView(inflate);
        inflate.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        CartCustomView cartCustomView = inflate.findViewById(R.id.layout_cartwindow);
        ImageView imgclose = inflate.findViewById(R.id.img_pop_close);
        ImageView img = inflate.findViewById(R.id.img_cart);
        TextView tv_name = inflate.findViewById(R.id.tv_pop_name);
        TextView tv_price = inflate.findViewById(R.id.tv_pop_price);
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                popupWindow = null;
            }
        });
        int[] pt = new int[2];
        llDown.getLocationInWindow(pt);
        popupWindow.showAtLocation(llDown, Gravity.NO_GRAVITY, 0, pt[1] - height);
        cartCustomView.initView();
        cartCustomView.setOnClick(new CartCustomView.OnClick() {
            @Override
            public void onclick(int value) {
                currentNum = value;
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_good_detail;
    }

    @Override
    public void getGoodDetailReturn(GoodDetailBean result) {
        goodDetailBean = result;
        //banner刷新
        updateBanner(result.getData().getGallery());
        //评论
        if (result.getData().getComment().getCount() > 0) {
            fragmentComment.setVisibility(View.VISIBLE);
            updateComment(result.getData().getComment());
        } else {
            fragmentComment.setVisibility(View.GONE);
        }
        //设置商品参数
        updateParameter(result.getData().getAttribute());
        //详情信息的显示
        updateDetail(result.getData().getInfo());
        //商品详情
        updategoodDetail(result.getData().getBrand());
        //常见问题
        updateQuestion(result.getData().getIssue());
        //大家都在看
    }

    //添加到购物车返回
    @Override
    public void addCartReturn(AddCartInfoBean result) {
        int goodsCount = result.getData().getCartTotal().getGoodsCount();
        shopcount.setText(String.valueOf(goodsCount));

    }

    //常见问题
    private void updateQuestion(List<GoodDetailBean.DataBeanX.IssueBean> issue) {
        llQuestion.removeAllViews();
        for (GoodDetailBean.DataBeanX.IssueBean item : issue) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.layout_good_detail_question, null);
            TextView tv_ques = inflate.findViewById(R.id.tv_ques);
            TextView tv_question = inflate.findViewById(R.id.tv_question);
            llQuestion.addView(inflate);
            tv_ques.setText("●" + item.getQuestion());
            tv_question.setText(item.getAnswer());
        }

    }

    //商品详情
    private void updategoodDetail(GoodDetailBean.DataBeanX.BrandBean brand) {
        goodName.setText(brand.getSimple_desc());
        goodPrice.setText("￥" + brand.getFloor_price());
        goodBoss.setText(brand.getName());
    }

    //详情信息显示web
    private void updateDetail(GoodDetailBean.DataBeanX.InfoBean info) {
        if (!TextUtils.isEmpty(info.getGoods_desc())) {
            String h5 = info.getGoods_desc();
            html = html.replace("$", h5);
            web.loadData(html, "text/html", "utf-8");
        }
    }

    //商品参数
    private void updateParameter(List<GoodDetailBean.DataBeanX.AttributeBean> attribute) {
        commentParameter.removeAllViews();
        for (GoodDetailBean.DataBeanX.AttributeBean item : attribute) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.layout_parameter, null);
            TextView tv_name = inflate.findViewById(R.id.txt_parameter_name);
            TextView tv_value = inflate.findViewById(R.id.txt_parameter_value);
            commentParameter.addView(inflate);
            tv_name.setText(item.getName());
            tv_value.setText(item.getValue());
        }

    }

    //评论
    private void updateComment(GoodDetailBean.DataBeanX.CommentBean comment) {
        tvCommentSize.setText("评价" + (comment.getCount()));
        goodCommentTime.setText(comment.getData().getAdd_time() + "");
        goodCommentConent.setText(comment.getData().getContent());
        commentImgs.removeAllViews();
        for (int i = 0; i < comment.getData().getPic_list().size(); i++) {
            ImageView imageView = new ImageView(this);
            Glide.with(this).load(comment.getData().getPic_list().get(i).getPic_url()).into(imageView);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(5, 5, 5, 5);
            imageView.setLayoutParams(layoutParams);
            commentImgs.addView(imageView);
        }
    }

    //banner
    private void updateBanner(List<GoodDetailBean.DataBeanX.GalleryBean> gallery) {
        if (goodBanner.getTag() == null || (int) goodBanner.getTag() == 0) {
            ArrayList<String> imgs = new ArrayList<>();
            for (GoodDetailBean.DataBeanX.GalleryBean item : gallery) {
                imgs.add(item.getImg_url());
            }
            goodBanner.setTag(1);
            goodBanner.setImageLoader(new GlideImageLoader());
            goodBanner.setImages(imgs);
            goodBanner.start();
        }
    }




    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
