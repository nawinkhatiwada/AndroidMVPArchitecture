package com.nawin.androidmvparchitecture.data.error

import java.io.IOException

/**
 * Created on 11/29/17.
 */
class FailedResponseException(val status: Int, override val message: String) : IOException(message)
class NetworkNotAvailableException : IOException()
