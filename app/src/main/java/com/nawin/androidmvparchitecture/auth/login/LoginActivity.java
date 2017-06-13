package com.nawin.androidmvparchitecture.auth.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nawin.androidmvparchitecture.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new LoginPresenter(this);
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
    public void showLoginError() {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
