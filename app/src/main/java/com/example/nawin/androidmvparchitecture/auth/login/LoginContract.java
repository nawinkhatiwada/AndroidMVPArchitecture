package com.example.nawin.androidmvparchitecture.auth.login;

import com.example.nawin.androidmvparchitecture.BasePresenter;
import com.example.nawin.androidmvparchitecture.BaseView;
import com.example.nawin.androidmvparchitecture.data.model.api.LoginRequest;

/**
 * Created by brainovation on 6/13/17.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void showLoginProgress();

        void showLoginSuccess(String message);

        void showLoginError();
    }

    interface Presenter extends BasePresenter {
        void onLogin(LoginRequest loginRequest);
    }
}
