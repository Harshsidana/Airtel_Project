package com.appstreet.base.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import com.appstreet.base.R
import com.appstreet.base.extension.errorCode
import com.appstreet.base.helper.ConnectionLiveData
import com.appstreet.base.model.DataState
import com.appstreet.base.model.Result
import java.lang.ref.WeakReference
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

open class BaseViewModel(private val app: Application) : AndroidViewModel(app) {

    private val connectionLiveData = ConnectionLiveData(WeakReference(app))
    private val connectionObserver = Observer<Boolean> { isConnected ->
        if (isConnected) onConnectedToNetwork()
    }

    init {
        connectionLiveData.observeForever(connectionObserver)
    }

    open fun doOnViewAttached() {}

    open fun onConnectedToNetwork() {}

    override fun onCleared() {
        super.onCleared()
        connectionLiveData.removeObserver(connectionObserver)
    }

    protected fun <T> Result.Failure<*>.toFailureDataState() = when (exception) {
        is UnknownHostException,
        is SocketException,
        is SocketTimeoutException,
        is TimeoutException,
        is ConnectException -> DataState.Failure(null, app.getString(R.string.no_connection).orEmpty(), emptyMap())

        else -> DataState.Failure<T>(exception.errorCode, app.getString(R.string.default_error_network), emptyMap())
    }
}