package com.chenlei.materialdesigndemo.bottom_navigation_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.chenlei.materialdesigndemo.R;
import com.chenlei.materialdesigndemo.tab_layout.Fragment1;
import com.google.android.material.appbar.CollapsingToolbarLayout;
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
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private List<Fragment> list;
    private BottomViewAdapter bottomViewAdapter;

    private MenuItem menuItem;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar=findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        initUI();
        bottomViewAdapter=new BottomViewAdapter(getSupportFragmentManager(),list);

        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar_layout);
        bnv=findViewById(R.id.bottom_navigation_view);
        viewPager=findViewById(R.id.view_pager);
        viewPager.setAdapter(bottomViewAdapter);
        viewPager.setOffscreenPageLimit(4); //设置ViewPager缓存个数
        //设置禁止ViewPager滑动
        viewPager.setScroll(false);
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
    }

    private void initUI(){
        list=new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());
    }
}
