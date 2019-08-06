package com.appstreet.base.exception

typealias KeyedErrorMap = Map<String, String>

open class RequestException(val statusCode: Int, val errorMap: KeyedErrorMap) : Exception() {

    private val fullMessage = errorMap.values.joinToString()



    override fun getLocalizedMessage(): String {
        return fullMessage
    }
}