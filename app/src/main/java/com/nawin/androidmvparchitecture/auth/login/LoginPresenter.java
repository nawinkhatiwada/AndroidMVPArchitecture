package com.nawin.androidmvparchitecture.auth.login;

import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.viewmodel.LoginViewModel;
import com.nawin.androidmvparchitecture.di.MvpComponent;
import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.error.FailedResponseException;
import com.nawin.androidmvparchitecture.data.error.NetworkNotAvailableException;

import io.reactivex.disposables.Disposable;

import static com.nawin.androidmvparchitecture.utils.Commons.dispose;

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
//        view.setPresenter(this);
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
        view.showLoginProgress();
        disposable = component.data().requestLogin(username, password)
                .subscribe(userInfo ->{
                    LoginViewModel loginViewModel = UserInfo.mapToViewModel(userInfo);
                    view.showLoginSuccess(loginViewModel);
                        },
                        throwable -> {
                            if (throwable instanceof FailedResponseException)
                                view.showLoginError(throwable.getMessage());
                            else if (throwable instanceof NetworkNotAvailableException)
                                view.showNetworkNotAvailableError();
                            else
                                view.showLoginError(component.context().getString(R.string.server_error));
                        });
    }
}
