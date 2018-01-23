package com.nawin.androidmvparchitecture.auth.login;

import android.support.annotation.StringRes;

import com.nawin.androidmvparchitecture.BasePresenter;
import com.nawin.androidmvparchitecture.BaseView;

/**
 * Created by nawin on 6/13/17.
 */

interface LoginContract {

    interface View extends BaseView<Presenter> {
        void showLoginProgress();

        void showLoginSuccess();

        void showLoginError(String message);

        void showNetworkNotAvailableError();
    }

    interface Presenter extends BasePresenter {
        void onLogin(String username, String password);
    }
}
