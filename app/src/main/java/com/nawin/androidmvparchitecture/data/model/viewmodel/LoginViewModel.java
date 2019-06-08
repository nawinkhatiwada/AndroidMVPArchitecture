package com.nawin.androidmvparchitecture.data.model.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.nawin.androidmvparchitecture.data.model.UserInfo;

public class LoginViewModel extends BaseObservable {

    private UserInfo userInfo;
    public LoginViewModel(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    @Bindable
    public String getUsername(){
        return userInfo.getUsername() + "Mero naam nawin ho";

    }

}
