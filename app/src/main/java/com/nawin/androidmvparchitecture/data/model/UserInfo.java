package com.nawin.androidmvparchitecture.data.model;

/**
 * Created by nawin on 6/13/17.
 */

public class UserInfo {

    /**
     * userId : 1
     * username : nawinkhatiwada
     * address : Anamnagar
     * phone : 988498948594
     */

    private int userId;
    private String username;
    private String address;
    private String phone;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
