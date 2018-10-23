package com.chenlei.materialdesigndemo.tab_layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chenlei.materialdesigndemo.R;
import com.chenlei.materialdesigndemo.bottom_navigation_view.BottomViewAdapter;
import com.chenlei.materialdesigndemo.bottom_navigation_view.Fragment2;
import com.chenlei.materialdesigndemo.bottom_navigation_view.Fragment3;
import com.chenlei.materialdesigndemo.bottom_navigation_view.Fragment4;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * @author Chen Lei
 * TabLayout使用
 */
public class Fragment1 extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BottomViewAdapter bottomViewAdapter;
    private List<Fragment> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //R.layout.fragment_my为该fragment的布局
        View view=inflater.inflate(R.layout.fragment1,container,false);


        initFragment();
        bottomViewAdapter=new BottomViewAdapter(getFragmentManager(),list);

        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.tablayout_viewpaper);
        //设置是否允许tab滑动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(bottomViewAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("123"));
        tabLayout.addTab(tabLayout.newTab().setText("456"));
        tabLayout.addTab(tabLayout.newTab().setText("789"));
        tabLayout.addTab(tabLayout.newTab().setText("ABC"));
        tabLayout.addTab(tabLayout.newTab().setText("123"));
        tabLayout.addTab(tabLayout.newTab().setText("456"));
        tabLayout.addTab(tabLayout.newTab().setText("789"));
        tabLayout.addTab(tabLayout.newTab().setText("ABC"));

        tabLayoutLinstener();

        NestedScrollView nestedScrollView=new NestedScrollView(getActivity());
        nestedScrollView.addView(view);
        return view;
    }

    private void initFragment(){
        list=new ArrayList<>();
        list.add(new TopFragment1());
        for(int i=0;i<7;i++)
            list.add(new TopFragment2());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void tabLayoutLinstener(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getActivity(),tab.getText(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
