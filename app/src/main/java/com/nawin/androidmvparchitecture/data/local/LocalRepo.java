package com.nawin.androidmvparchitecture.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.nawin.androidmvparchitecture.data.model.UserInfo;

/**
 * Created by brainovation on 6/13/17.
 */

public class LocalRepo {

    private static final String USER_INFO = "_user_info";
    private final SharedPreferences sharedPreferences;
    private final Gson gson;
    private static UserInfo cachedUserInfo;
    private static LocalRepo localRepo;

    public static LocalRepo getInstance(Context context) {
        if (localRepo == null) {
            localRepo = new LocalRepo(context, new Gson());
        }
        return localRepo;
    }

    public LocalRepo(Context context, Gson gson) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.gson = gson;
    }

    public void setUserInfo(@NonNull UserInfo userInfo) {
        sharedPreferences.edit().putString(USER_INFO, gson.toJson(userInfo)).apply();
        cachedUserInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        if (cachedUserInfo == null) {
            String userInfoS = sharedPreferences.getString(USER_INFO, null);
            if (!TextUtils.isEmpty(userInfoS)) {
                cachedUserInfo = gson.fromJson(userInfoS, UserInfo.class);
            }
        }
        return cachedUserInfo;
    }
}
