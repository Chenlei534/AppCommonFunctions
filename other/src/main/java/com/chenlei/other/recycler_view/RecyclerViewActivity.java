package com.chenlei.other.recycler_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
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
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RVAdapter rvAdapter;
    private List<RecyclerViewItem> rviList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        //初始化数据、RecyclerView、数据适配器和布局管理
        initData();
        swipeRefreshLayout=findViewById(R.id.swipe_refresh_layout);
        recyclerView=findViewById(R.id.recycler_view);
        rvAdapter=new RVAdapter(rviList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //设置RecyclerView的布局管理和适配器
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(rvAdapter);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        //下拉刷新
        downRefresh();
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

    private void refreshData(){
        rviList.clear();
        for(int i=0;i<50;i++){
            RecyclerViewItem rvi=new RecyclerViewItem("刷新列表："+(i+1));
            rviList.add(rvi);
        }
    }

    private void downRefresh(){
        //下拉刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //模拟数据刷新过程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            refreshData();
                            Thread.sleep(2000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    swipeRefreshLayout.setRefreshing(false);
                                    rvAdapter.notifyDataSetChanged();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private void upLoad(){

    }
}
