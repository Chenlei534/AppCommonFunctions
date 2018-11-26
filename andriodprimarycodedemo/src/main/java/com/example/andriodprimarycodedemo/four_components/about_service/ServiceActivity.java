package com.example.andriodprimarycodedemo.four_components.about_service;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andriodprimarycodedemo.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class ServiceActivity extends AppCompatActivity {
    private Button mStartButton;
    private Button mStopButton;
    private Button mBindButton;
    private Button mUnbindButton;

    private Intent startServiceIntent;
    private Intent bindServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mStartButton=findViewById(R.id.start_service_button);
        mStopButton=findViewById(R.id.stop_service_button);
        mBindButton=findViewById(R.id.bind_service_button);
        mUnbindButton=findViewById(R.id.unbind_service_button);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startServiceIntent=new Intent(ServiceActivity.this,StartService.class);
                startService(startServiceIntent);
            }
        });
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(startServiceIntent);
            }
        });
        mBindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bindServiceIntent=new Intent()
            }
        });
        mUnbindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
