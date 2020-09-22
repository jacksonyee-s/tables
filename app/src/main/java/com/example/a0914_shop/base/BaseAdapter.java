package com.example.a0914_shop.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
protected Context context;
protected List<T> data;
private IClick iClick;



    public void setiClick(IClick iClick) {
        this.iClick = iClick;
    }

    public BaseAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(getLayout(), parent,false);
        final BaseViewHolder baseViewHolder = new BaseViewHolder(inflate);
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iClick!=null){
                    iClick.click(baseViewHolder.getLayoutPosition());
                }
            }
        });
        return baseViewHolder;
    }

    protected abstract int getLayout();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder baseViewHolder = (BaseViewHolder)holder;
        T _data = data.get(position);
        //绑定数据
        bindData(baseViewHolder,_data,position);
    }

    protected abstract void bindData(BaseViewHolder baseViewHolder, T data,int position);

    @Override
    public int getItemCount() {
        return data.size();
    }
    //基类适配器
    public class BaseViewHolder extends RecyclerView.ViewHolder{
        SparseArray views = new SparseArray();
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public View getViewById(int id){
            View view = (View) views.get(id);
            if (view==null){
                view = itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }
    }
    //接口回调
  public  interface IClick{
        void click(int position);
    }
}
