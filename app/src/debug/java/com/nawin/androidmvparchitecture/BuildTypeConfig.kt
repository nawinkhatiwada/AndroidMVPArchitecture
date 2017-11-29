package com.nawin.androidmvparchitecture

import android.content.Context
import com.facebook.stetho.Stetho
import javax.inject.Inject

/**
 * Created on 11/29/17.
 */
class BuildTypeConfig @Inject constructor(private val context: Context) {

     fun set() {
        Stetho.initializeWithDefaults(context)
    }
}