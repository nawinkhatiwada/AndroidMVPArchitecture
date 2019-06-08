package com.nawin.androidmvparchitecture.data.model;

import com.nawin.androidmvparchitecture.data.model.viewmodel.LoginViewModel;

/**
 * Created by nawin on 6/13/17.
 */

public class UserInfo {

    private String id;
    private String username;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static LoginViewModel mapToViewModel(UserInfo userInfo){
        return new LoginViewModel(userInfo);
    }
}
