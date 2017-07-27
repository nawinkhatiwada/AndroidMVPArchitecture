package com.nawin.androidmvparchitecture.data.model.api;

/**
 * Created by nawin on 6/14/17.
 */

public class BaseResponse<T> {

    private T items;
    private T response;

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
}
