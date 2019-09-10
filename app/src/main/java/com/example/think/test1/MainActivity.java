package com.example.think.test1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleListView);
        initData();
        initView();
    }

    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyAdapter(MainActivity.this, getData());
    }
    public static String[] eatFoodyImages = {
            "http://39.106.138.186:8900/uploads/20190813/aa576733868201f3a495f5813a4e2e8b.jpg",
            "http://39.106.138.186:8900/uploads/20190813/0cb979a63af7073f9b0606526b32eec2.jpg",
            "http://39.106.138.186:8900/uploads/20190813/663f16236c9224a6eb0147d8366eddbb.jpg",
            "http://39.106.138.186:8900/uploads/20190813/99d3c3dc3beb67914e96bbe7fb708ff7.jpg",
            "http://39.106.138.186:8900/uploads/20190813/f6c1d5a3e3dab93052f369a6db68ed16.jpg",
            "http://39.106.138.186:8900/uploads/20190813/3e2833421db7bbe3754a58c18a17c490.jpg"
    };
    private void initView() {
        recyclerView = findViewById(R.id.recycleListView);
        // 设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        recyclerView.setAdapter(mAdapter);
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        for (String next : eatFoodyImages) {
//            Log.i("RetrofitLog", "message = " + next);
            data.add(next);
        }
        return data;
    }
}
