package com.nawin.androidmvparchitecture.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import com.google.gson.Gson
import com.nawin.androidmvparchitecture.data.model.UserInfo
import javax.inject.Inject

/**
 * Created on 11/29/17.
 */
class LocalRepo @Inject constructor(context: Context, private val gson: Gson) {

    private val USER_INFO = "_user_info"
    private val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
    private var cachedUserInfo: UserInfo? = null

    fun setUserInfo(userInfo: UserInfo) {
        sharedPreferences.edit().putString(USER_INFO, gson.toJson(userInfo)).apply()
        cachedUserInfo = userInfo
    }

    fun getUserInfo(): UserInfo? {
        if (cachedUserInfo == null) {
            val userInfoS = sharedPreferences.getString(USER_INFO, null)
            if (!TextUtils.isEmpty(userInfoS)) {
                cachedUserInfo = gson.fromJson(userInfoS, UserInfo::class.java)
            }
        }
        return cachedUserInfo
    }

    fun logout() {
        this.cachedUserInfo = null
        sharedPreferences.edit().clear().apply()
    }
}