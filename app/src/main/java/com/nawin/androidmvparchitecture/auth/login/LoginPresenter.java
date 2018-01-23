package com.nawin.androidmvparchitecture.auth.login;

import android.content.Context;

import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nawin.androidmvparchitecture.utils.Commons.cancel;
import static com.nawin.androidmvparchitecture.utils.Commons.isNetworkAvailable;

/**
 * Created by nawin on 6/13/17.
 */

class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private Call<BaseResponse<UserInfo>> call;

    LoginPresenter(LoginContract.View view) {
        this.view = view;
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

        if (!isNetworkAvailable(view.getContext())) {
            view.showNetworkNotAvailableError();
            return;
        }
        view.showLoginProgress();
        call = Data.getInstance(view.getContext()).requestLogin(username, password, new Callback<BaseResponse<UserInfo>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserInfo>> call, Response<BaseResponse<UserInfo>> response) {
                if (response != null && response.isSuccessful()) {
                    UserInfo userInfo = response.body().getResponse();
                    if (userInfo != null) {
                        view.showLoginSuccess();
                    } else {
                        view.showLoginError(response.body().getStatusMessage());
                    }
                } else {
                    view.showLoginError(view.getContext().getString(R.string.server_error));
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<UserInfo>> call, Throwable t) {
                view.showLoginError(view.getContext().getString(R.string.server_error));
            }
        });
    }
}
