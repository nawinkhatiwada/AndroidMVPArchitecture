package com.nawin.androidmvparchitecture.auth.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nawin.androidmvparchitecture.BaseActivity;
import com.nawin.androidmvparchitecture.R;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(component, this);
//        presenter.onLogin("username", "1234");
    }

    @Override
    protected void onPause() {
        presenter.stop();
        super.onPause();
    }

    @Override
    public void onSessionTimeout(String message) {

    }

    @Override
    public void showLoginProgress() {

    }

    @Override
    public void showLoginSuccess(String message) {
    }

    @Override
    public void showLoginError(String message) {

    }

    @Override
    public void showNetworkNotAvailableError(String message) {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
