package com.nawin.androidmvparchitecture

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nawin.androidmvparchitecture.data.Data
import javax.inject.Inject

/**
 * Created on 11/29/17.
 */
open class BaseActivity : AppCompatActivity() {

    protected lateinit var component: MvpComponent

    @Inject
    protected lateinit var data: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.component = MVPApp.component(this)
        this.component.inject(this)
    }
}
