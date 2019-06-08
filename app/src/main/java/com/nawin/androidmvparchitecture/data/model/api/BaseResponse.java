package com.nawin.androidmvparchitecture.data.model.api;


import com.google.gson.annotations.SerializedName;

/**
 * Created by nawin on 6/14/17.
 */

public class BaseResponse<T> {

    @SerializedName("statusMessage") private String statusMessage;
    @SerializedName("statusCode") private int statusCode;
    @SerializedName("response") private T response;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
