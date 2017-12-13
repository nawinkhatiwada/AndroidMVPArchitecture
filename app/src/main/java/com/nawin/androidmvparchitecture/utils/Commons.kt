package com.nawin.androidmvparchitecture.utils

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import com.nawin.androidmvparchitecture.R

/**
 * Created on 11/29/17.
 */


fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun showLoadingDialog(context: Context, message: String): ProgressDialog {
    val progressDialog = ProgressDialog(context)
    progressDialog.setCancelable(false)
    progressDialog.setMessage(message)
    progressDialog.show()
    return progressDialog
}

fun  showLoadingDialog(context: Context): ProgressDialog =
        showLoadingDialog(context, context.getString(R.string.loading))