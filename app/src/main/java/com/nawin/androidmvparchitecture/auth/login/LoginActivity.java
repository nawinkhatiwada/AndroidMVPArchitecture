package com.nawin.androidmvparchitecture.auth.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.model.api.LoginRequest;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this, this);
        presenter.onLogin("username", "1234");
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
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
