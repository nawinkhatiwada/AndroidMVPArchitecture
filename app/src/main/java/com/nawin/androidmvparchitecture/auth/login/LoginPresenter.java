package com.nawin.androidmvparchitecture.auth.login;

import android.content.Context;

import com.nawin.androidmvparchitecture.MvpComponent;
import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;
import com.nawin.androidmvparchitecture.data.model.api.LoginRequest;

import java.io.IOException;

import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nawin.androidmvparchitecture.utils.Commons.cancel;

/**
 * Created by brainovation on 6/13/17.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private Call<BaseResponse<UserInfo>> call;
    private Disposable disposable;
    private MvpComponent component;

    public LoginPresenter(MvpComponent component, LoginContract.View view) {
        this.view = view;
        this.component = component;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        cancel(call);
    }

    @Override
    public void onLogin(String username, String password) {

        view.showLoginProgress();
        disposable = component.data().requestLogin(username, password)
                .subscribe(userInfo -> {
                    if (userInfo != null) {
                        view.showLoginSuccess("Login success");
                    } else
                        view.showLoginError("Server Error");

                }, throwable -> {
                    if (throwable instanceof IOException)
                        view.showNetworkNotAvailableError("Network Not Available");
                    else
                        view.showLoginError("Server Error");
                });
    }
}
