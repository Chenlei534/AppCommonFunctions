package com.chenlei.materialdesigndemo.bottom_navigation_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chenlei.materialdesigndemo.R;
import com.chenlei.materialdesigndemo.tab_layout.Fragment1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Lei
 * BottomNavigationView实现底部导航
 */
public class MainActivity extends AppCompatActivity {
    private NoScrollViewPager viewPager;
    private BottomNavigationView bnv;
    private NestedScrollView nestedScrollView;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private CoordinatorLayout coordinatorLayout;

    private List<Fragment> list;
    private BottomViewAdapter bottomViewAdapter;
    private boolean isBottomShow=true;

    private MenuItem menuItem;
    private Toolbar mToolbar;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //工具条相关设置
        mToolbar=findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
//        getSupportActionBar().set
        //初始化控件及fragment
        initFragment();
        bnv=findViewById(R.id.bottom_navigation_view);
        viewPager=findViewById(R.id.view_pager);
        coordinatorLayout=findViewById(R.id.coordinator_layout);
        //    nestedScrollView=findViewById(R.id.nested_scroll_view);

     //   navigationView=findViewById(R.id.nav_view);
    //    mDrawerLayout=findViewById(R.id.drawer_layout);
        //设置viewpager相关参数
        bottomViewAdapter=new BottomViewAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(bottomViewAdapter);
        viewPager.setOffscreenPageLimit(4); //设置ViewPager缓存个数
        viewPager.setScroll(false);//设置禁止ViewPager滑动
        //设置监听
        listener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                Toast.makeText(MainActivity.this,"asdaf",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
           //     mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    /**
     * 监听
     */
    private void listener(){
        //监听
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.all:
                        viewPager.setCurrentItem(0);
                        getSupportActionBar().setTitle(menuItem.getTitle());
                        Toast.makeText(getApplicationContext(),"全部",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tool:
                        viewPager.setCurrentItem(1);
                        getSupportActionBar().setTitle(menuItem.getTitle());
                        Toast.makeText(getApplicationContext(),"工具",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.refersh:
                        viewPager.setCurrentItem(2);
                        getSupportActionBar().setTitle(menuItem.getTitle());
                        Toast.makeText(getApplicationContext(),"刷新",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.error:
                        viewPager.setCurrentItem(3);
                        getSupportActionBar().setTitle(menuItem.getTitle());
                        Toast.makeText(getApplicationContext(),"错误",Toast.LENGTH_SHORT).show();
                        break;
                }
                //返回true表示事件已被处理，否则无法选中其他项或者使用item.setChecked(true)设置项目选中
                return true;
            }
        });
        //监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(menuItem!=null){
                    menuItem.setChecked(false);
                }else {
                    bnv.getMenu().getItem(0).setChecked(false);
                }
                menuItem=bnv.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.nav_task:
//                        Toast.makeText(getApplicationContext(),"dianjile",Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return false;
//            }
//        });

    }

    private void initFragment(){
        list=new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());
    }
}
