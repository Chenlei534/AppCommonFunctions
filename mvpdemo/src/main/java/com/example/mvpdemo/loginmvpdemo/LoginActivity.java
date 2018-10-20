package com.example.mvpdemo.loginmvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvpdemo.R;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener{
    private EditText user;
    private EditText password;
    private Button login;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        user=findViewById(R.id.user_et);
        password=findViewById(R.id.password_et);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);

        loginPresenter=new LoginPresenter(this);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void delPassword() {
        password.setText("");
    }

    @Override
    public void loginSuccess() {
        Intent intent=new Intent(LoginActivity.this,LoginSuccessActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                loginPresenter.toLogin(user.getText().toString().trim(),password.getText().toString());
                break;
        }
    }
}
