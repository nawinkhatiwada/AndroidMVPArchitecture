package com.nawin.androidmvparchitecture

import android.content.Context
import com.nawin.androidmvparchitecture.data.Data
import com.nawin.androidmvparchitecture.data.remote.DataModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created on 11/29/17.
 */
@Component(modules = arrayOf(MvpModule::class, DataModule::class))
@Singleton
interface MvpComponent {
    fun context(): Context

    fun buildType(): BuildTypeComponent

    fun data(): Data

    fun inject(baseActivity: BaseActivity)
}