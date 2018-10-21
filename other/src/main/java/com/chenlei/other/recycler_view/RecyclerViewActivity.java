package com.chenlei.other.recycler_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.chenlei.other.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Lei
 *
 * 使用RecyclerView实现列表的下拉刷新和上拉加载
 */
public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<RecyclerViewItem> rviList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        //初始化数据、RecyclerView、数据适配器和布局管理
        initData();
        recyclerView=findViewById(R.id.recycler_view);
        RVAdapter rvAdapter=new RVAdapter(rviList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //设置RecyclerView的布局管理和适配器
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvAdapter);
    }

    /**
     * 列表适配器所需要的数据初始化
     */
    private void initData(){
        for(int i=0;i<50;i++){
            RecyclerViewItem rvi=new RecyclerViewItem("列表："+(i+1));
            rviList.add(rvi);
        }
    }
}
