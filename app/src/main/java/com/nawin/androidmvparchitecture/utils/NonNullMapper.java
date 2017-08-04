package com.nawin.androidmvparchitecture.utils;

import com.nawin.androidmvparchitecture.data.error.FailedResponseException;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class NonNullMapper<T> implements Function<BaseResponse<T>, Single<T>> {
    @Override
    public Single<T> apply(@NonNull BaseResponse<T> baseResponse) throws Exception {
        if (baseResponse.getStatusCode() != 1)
            return Single.error(new FailedResponseException(baseResponse.getStatusCode(), "message"));
        T item = baseResponse.getResponse();
        if (item == null)
            return Single.error(new NullPointerException("BaseResponse.Response == null"));
        else
            return Single.just(item);
    }
}