package com.chenlei.materialdesigndemo.bottom_navigation_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.chenlei.materialdesigndemo.R;
import com.chenlei.materialdesigndemo.tab_layout.Fragment1;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

    private List<Fragment> list;
    private BottomViewAdapter bottomViewAdapter;
    private boolean isBottomShow=true;

    private MenuItem menuItem;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar=findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        initFragment();

        bnv=findViewById(R.id.bottom_navigation_view);
        viewPager=findViewById(R.id.view_pager);
        nestedScrollView=findViewById(R.id.nested_scroll_view);

        bottomViewAdapter=new BottomViewAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(bottomViewAdapter);
        viewPager.setOffscreenPageLimit(4); //设置ViewPager缓存个数
        //设置禁止ViewPager滑动
        viewPager.setScroll(false);
        listener();
    }

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
//        //监听
//        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.e("Test","22222");
//                //上滑 并且 正在显示底部栏
//                if (scrollY - oldScrollY > 0 && isBottomShow) {
//                    isBottomShow = false;
//                    //将Y属性变为底部栏高度  (相当于隐藏了)
//                    Log.e("Test","00000");
//                    bnv.animate().translationY(bnv.getHeight());
//                } else {
//                    if (scrollY - oldScrollY < 0 && !isBottomShow) {
//                        Log.e("Test","11111");
//                        isBottomShow = true;
//                        bnv.animate().translationY(0);
//                    }
//                }
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
