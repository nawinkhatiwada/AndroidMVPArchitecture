package com.nawin.androidmvparchitecture.auth.login;

import com.nawin.androidmvparchitecture.MvpComponent;
import com.nawin.androidmvparchitecture.data.error.FailedResponseException;

import io.reactivex.disposables.Disposable;

import static com.nawin.androidmvparchitecture.utils.Commons.dispose;
import static com.nawin.androidmvparchitecture.utils.Commons.isNetworkAvailable;

/**
 * Created by nawin on 6/13/17.
 */

class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private Disposable disposable;
    private MvpComponent component;

    LoginPresenter(MvpComponent component, LoginContract.View view) {
        this.view = view;
        this.component = component;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        dispose(disposable);
    }

    @Override
    public void onLogin(String username, String password) {
        if (!isNetworkAvailable(component.context())) {
            view.showNetworkNotAvailableError();
            return;
        }
        view.showLoginProgress();
        disposable = component.data().requestLogin(username, password)
                .subscribe(userInfo -> view.showLoginSuccess(), throwable -> {
                    if (throwable instanceof FailedResponseException)
                        view.showLoginError(throwable.getMessage());
                    else
                        view.showLoginError("Server Error");
                });
    }
}
