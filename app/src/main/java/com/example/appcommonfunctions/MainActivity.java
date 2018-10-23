package com.example.appcommonfunctions;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar_layout);

        getSupportActionBar().setTitle("sdfe12312");
    }

}
