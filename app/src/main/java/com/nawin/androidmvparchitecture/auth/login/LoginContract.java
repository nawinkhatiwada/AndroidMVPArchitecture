package com.nawin.androidmvparchitecture.auth.login;

import com.nawin.androidmvparchitecture.BasePresenter;
import com.nawin.androidmvparchitecture.BaseView;
import com.nawin.androidmvparchitecture.data.model.api.LoginRequest;

/**
 * Created by brainovation on 6/13/17.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void showLoginProgress();

        void showLoginSuccess(String message);

        void showLoginError(String message);
    }

    interface Presenter extends BasePresenter {
        void onLogin(String username, String password);
    }
}
