package com.example.mvpdemo.loginmvpdemo;

public class LoginPresenter {
    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenter(LoginView loginView){
        this.loginView=loginView;
    }

    public void toLogin(String user,String password){
        loginModel=new LoginModel(user,password);
        loginModel.loginRequest(new LoginModelCallback() {
            @Override
            public void pwError() {
                loginView.showMessage("密码错误");
                loginView.delPassword();
            }

            @Override
            public void pwIsNull() {
                loginView.showMessage("请输入密码");
            }

            @Override
            public void loginSuccess() {
                loginView.loginSuccess();
            }
        });
    }

}
