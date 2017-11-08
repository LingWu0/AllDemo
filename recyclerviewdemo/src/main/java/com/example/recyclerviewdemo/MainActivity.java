package com.example.recyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;

public class MainActivity extends AppCompatActivity {

    private SwipyRefreshLayout srl;
    private RecyclerView recyclerView;
    private Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        srl = (SwipyRefreshLayout) findViewById(R.id.srl);
        //刷新加载
        //设置颜色
//        srl.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark, android.R.color.holo_red_dark);
        srl.setDirection(SwipyRefreshLayoutDirection.BOTH);
        srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                Toast.makeText(MainActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }

            @Override
            public void onLoad(int index) {
                Toast.makeText(MainActivity.this, "上拉加载", Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

       MyAdapter myadapter = new MyAdapter();

        //在实际开发中LayoutInfloate这个类还是非常有用的，它的作用类似于findView();
        //不同点是LaytouInflate是用来找res/layout/下的xml文件，并且实例化
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.header, null);
        myadapter.addHeadView(view);

        View view1 = layoutInflater.inflate(R.layout.footer, null);
        myadapter.addFootView(view1);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myadapter);


    }
}
