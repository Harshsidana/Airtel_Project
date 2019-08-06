package com.appstreet.base.model.errorresponse

import com.squareup.moshi.Json
data class JsonApiErrorResponse(
    @Json(name = "message") val message: String?,
    @Json(name = "errors") val errorList: List<Error>
) {
    data class Error(
        @Json(name = "title") val title: String,
        @Json(name = "message") val message: String?,
        @Json(name = "detail") val detail: String,
        @Json(name = "source") val source: Source?
    )

    data class Source(
        @Json(name = "parameter") val parameter: String
    )
}