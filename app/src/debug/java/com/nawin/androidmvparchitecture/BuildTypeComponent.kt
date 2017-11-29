package com.nawin.androidmvparchitecture

import dagger.Subcomponent

/**
 * Created on 11/29/17.
 */
@MvpScope
@Subcomponent
interface BuildTypeComponent {
     fun buildTypeConfig(): BuildTypeConfig
}