package com.nawin.androidmvparchitecture.utils;

import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class NonNullMapper<T> implements Function<BaseResponse<T>, Single<T>> {
    @Override
    public Single<T> apply(@NonNull BaseResponse<T> baseResponse) throws Exception {
        T item = baseResponse.getResponse();
        if (item == null)
            return Single.error(new NullPointerException("BaseResponse.Response == null"));
        else
            return Single.just(item);
    }
}