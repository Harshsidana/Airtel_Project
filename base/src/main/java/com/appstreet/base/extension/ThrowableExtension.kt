package com.appstreet.base.extension

import com.appstreet.base.exception.RequestException


val Throwable.errorCode
    get() = when (this) {
        is RequestException -> statusCode
        else -> null
    }