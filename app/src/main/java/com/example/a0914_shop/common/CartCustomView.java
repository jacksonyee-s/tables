package com.example.a0914_shop.common;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.a0914_shop.R;

import java.text.BreakIterator;

public class CartCustomView extends LinearLayout {
    private Context context;
    private int value = 1;
    private int min = 1;
    private int max = 9999;
    private OnClick onClick;
    private TextView tvvalue;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public CartCustomView(Context context) {
        super(context);
        this.context = context;
    }

    public CartCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CartCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CartCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }
    public void initView(){
        TextView tvsub = findViewById(R.id.tv_subtract);
        tvvalue = findViewById(R.id.tv_value);
        TextView tvadd = findViewById(R.id.tv_add);
        if (tvsub!=null&& tvvalue !=null&&tvadd!=null){
            tvsub.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    value--;
                    if (value<1){
                        value=1;
                    }else{
                        tvvalue.setText(String.valueOf(value));
                        if (onClick!=null){
                            onClick.onclick(value);
                        }
                    }
                }
            });
            tvadd.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    value++;
                    if (value>max){
                        value=max;
                    }else{
                        if (onClick!=null){
                            onClick.onclick(value);
                        }
                    }
                    tvvalue.setText(String.valueOf(value));
                }
            });
        }else{
            Toast.makeText(context, "初始化调用错误", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 设置值
     * @param num
     */
    public void setValue(int num){
        this.value = num;
        tvvalue.setText(String.valueOf(this.value));
    }
    //初始化最大最小值
    public void initView(int min,int max){
        this.min = min;
        this.max = max;
        initView();
    }


    //接口回调
    public interface  OnClick{
        void onclick(int value);
    }

}
