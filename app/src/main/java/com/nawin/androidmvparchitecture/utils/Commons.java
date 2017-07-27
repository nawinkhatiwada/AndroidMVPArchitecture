package com.nawin.androidmvparchitecture.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.nawin.androidmvparchitecture.R;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by nawin on 6/13/17.
 */

public class Commons {

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

    public static ProgressDialog showLoadingDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        return showLoadingDialog(context, context.getString(R.string.loading));
    }
}
