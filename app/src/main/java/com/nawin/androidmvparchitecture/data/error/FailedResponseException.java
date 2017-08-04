package com.nawin.androidmvparchitecture.data.error;

/**
 * Created by brainovation on 8/4/17.
 */

public class FailedResponseException extends Throwable {
    private final int statusCode;

    public FailedResponseException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
