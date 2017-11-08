package com.example.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.retrofit2.adapter.MyAdapter;
import com.example.retrofit2.api.PostApi;
import com.example.retrofit2.bean.Bean2;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity======";
    private RecyclerView recyclerview;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerview.setLayoutManager(linearLayoutManager);


        postFieldMapDatas();
    }

    private void postFieldMapDatas() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/oil/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HashMap<String,String> map = new HashMap<>();
        map.put("city","北京");
        map.put("key","4979cecb9f27342f5513cb31dc8e87ad");
        Call<Bean2> call = build.create(PostApi.class).postFieldMapData(map);
        call.enqueue(new Callback<Bean2>() {
            @Override
            public void onResponse(Call<Bean2> call, Response<Bean2> response) {
                Bean2 body = response.body();
                Log.e(TAG,body.getResult().getData().get(0).getName());
                adapter = new MyAdapter(MainActivity.this,body.getResult().getData());
                recyclerview.setAdapter(MainActivity.this.adapter);
               // Toast.makeText(MainActivity.this, body.getResult().getData().get(0).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Bean2> call, Throwable t) {

            }
        });
    }
}
