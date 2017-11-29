package com.nawin.androidmvparchitecture

import android.app.Application
import android.content.Context

/**
 * Created on 11/29/17.
 */
class MVPApp : Application() {
    private lateinit var component: MvpComponent

    override fun onCreate() {
        super.onCreate()
        this.component = DaggerMvpComponent.builder().mvpModule(MvpModule(this)).build()
        component.buildType().buildTypeConfig().set()
    }

    companion object {
        fun component(context: Context): MvpComponent =
                (context.applicationContext as MVPApp).component
    }

}