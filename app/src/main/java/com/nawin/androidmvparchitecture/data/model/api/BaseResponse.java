package com.nawin.androidmvparchitecture.data.model.api;


/**
 * Created by nawin on 6/14/17.
 */

public class BaseResponse<T> {

    private String statusMessage;
    private int statusCode;
    private T response;

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
