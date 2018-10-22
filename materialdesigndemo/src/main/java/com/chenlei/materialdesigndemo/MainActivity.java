package com.chenlei.materialdesigndemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv=findViewById(R.id.bottom_navigation_view);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.all:
                        Toast.makeText(getApplicationContext(),"全部",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tool:
                        Toast.makeText(getApplicationContext(),"工具",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.refersh:
                        Toast.makeText(getApplicationContext(),"刷新",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.error:
                        Toast.makeText(getApplicationContext(),"错误",Toast.LENGTH_SHORT).show();
                        break;
                }
                //返回true表示事件已被处理，否则无法选中其他项或者使用item.setChecked(true)设置项目选中
                return true;
            }
        });
    }

}
