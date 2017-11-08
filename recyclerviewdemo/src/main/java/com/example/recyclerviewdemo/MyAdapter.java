package com.example.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxsj on 2017/11/1.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private static final int TYPE_HEADER = 0,TYPE_ITEM=1,TYPE_FOOT=2;

    private List<String> list;
    private View headView;
    private View footView;

    private Banner imgbanner;

    private ArrayList<String> list_path;
    private ArrayList<String> list_textview;

    public MyAdapter(){
        list = new ArrayList<>();
        for (int i=0;i<30;i++){
            list.add("惊鸿一面"+i);
        }
    }

    //添加头部的方法
    public void addHeadView(View view){
        headView = view;
    }

    //添加脚部的方法
    public void addFootView(View view){
        footView = view;
    }

    //判断返回的布局类型
    @Override
    public int getItemViewType(int position) {
        int type = TYPE_ITEM;
        if (position==0){
            type = TYPE_HEADER;
        }else if(position==getItemCount()-1){
            type = TYPE_FOOT;
        }
        return type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = null;

        switch (viewType){
            case TYPE_HEADER:
                inflate = headView;
                imgbanner = (Banner) inflate.findViewById(R.id.headerimg);
                initview();
                return new MyViewHolder(inflate);

            case TYPE_ITEM:
                inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
                MyViewHolder myViewHolder = new MyViewHolder(inflate);
                myViewHolder.textView = (TextView) inflate.findViewById(R.id.textview);
                return myViewHolder;

            case TYPE_FOOT:
                inflate = footView;
                imgbanner = (Banner) inflate.findViewById(R.id.headerimg);
                initview();
                return new MyViewHolder(inflate);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            switch (getItemViewType(position)){
                case TYPE_HEADER:
                    break;

                case TYPE_ITEM:
                    holder.textView.setText(list.get(position));
                    break;

                case TYPE_FOOT:
                    break;
            }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private void initview(){
        //放图片地址的集合
        list_path = new ArrayList<>();
        list_textview = new ArrayList<>();

        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");

        for (int i = 0;i<4;i++){
            list_textview.add("我是头部");
        }

        //设置内置样式
        imgbanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        imgbanner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        imgbanner.setImages(list_path);
        //设置轮播的效果
        imgbanner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        imgbanner.setBannerTitles(list_textview);
        //设置轮播间隔时间
        imgbanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是
        imgbanner.isAutoPlay(true);
        //设置指示器的位置
        imgbanner.setIndicatorGravity(BannerConfig.CENTER)
                .start();


    }
    //自定义图片加载器
    private class MyLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
