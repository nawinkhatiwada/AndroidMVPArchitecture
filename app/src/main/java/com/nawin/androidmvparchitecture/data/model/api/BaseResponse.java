package com.nawin.androidmvparchitecture.data.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nawin on 6/14/17.
 */

public class BaseResponse<T> {

    private T items;
    private T response;
    @SerializedName("status_code")
    private int statusCode;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
