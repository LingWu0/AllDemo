package com.example.retrofit2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofit2.R;
import com.example.retrofit2.bean.Bean2;

import java.util.List;

/**
 * Created by xxsj on 2017/11/2.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Context context;
    private List<Bean2.ResultBean.DataBean> resultBeen;
    private View inflte;

    public MyAdapter(Context context,List<Bean2.ResultBean.DataBean> resultBeen){
        this.context = context;
        this.resultBeen = resultBeen;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        inflte = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(inflte);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       holder.textView.setText(resultBeen.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return resultBeen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public  TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
