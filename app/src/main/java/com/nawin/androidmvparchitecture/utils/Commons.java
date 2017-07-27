package com.nawin.androidmvparchitecture.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

import io.reactivex.disposables.Disposable;
import retrofit2.Call;

/**
 * Created by nawin on 6/13/17.
 */

public class Commons {

    public static void cancel(Call... calls) {
        if (calls != null && calls.length > 0) {
            for (Call call : calls) {
                if (call != null)
                    call.cancel();
            }
        }
    }

    public static void dispose(Disposable... disposables) {
        if (disposables != null && disposables.length > 0) {
            for (Disposable disposable : disposables) {
                if (disposable != null)
                    disposable.dispose();
            }
        }
    }

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && (networkInfo.isConnected());
    }

}
