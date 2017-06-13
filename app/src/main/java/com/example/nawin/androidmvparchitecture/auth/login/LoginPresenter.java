package com.example.nawin.androidmvparchitecture.auth.login;

import com.example.nawin.androidmvparchitecture.data.Data;
import com.example.nawin.androidmvparchitecture.data.model.UserInfo;
import com.example.nawin.androidmvparchitecture.data.model.api.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.nawin.androidmvparchitecture.utils.Commons.cancel;

/**
 * Created by brainovation on 6/13/17.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private Call<UserInfo> call;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
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
    public void onLogin(LoginRequest loginRequest) {
        view.showLoginProgress();
        call = Data.getInstance().getLogin(loginRequest, new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.showLoginSuccess("Login Success");
                } else {
                    view.showLoginError();
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });

    }
}
