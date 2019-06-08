package com.nawin.androidmvparchitecture.auth.login;

import com.nawin.androidmvparchitecture.BasePresenter;
import com.nawin.androidmvparchitecture.BaseView;
import com.nawin.androidmvparchitecture.data.model.viewmodel.LoginViewModel;

/**
 * Created by nawin on 6/13/17.
 */

interface LoginContract {

    interface View extends BaseView<Presenter> {
        void showLoginProgress();

        void showLoginSuccess(LoginViewModel loginViewModel);

        void showLoginError(String message);

        void showNetworkNotAvailableError();
    }

    interface Presenter extends BasePresenter {
        void onLogin(String username, String password);
    }
}
