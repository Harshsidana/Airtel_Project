package com.appstreet.home.model.response

import com.squareup.moshi.Json

class ApiReponse(

    @Json(name = "username") val username: String,
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String,
    @Json(name = "url") val url: String,
    @Json(name = "avatar") val avatar: String,
    @Json(name = "repo") val repo: Repo


) {

    data class Repo(
        @Json(name = "name") val name: String,
        @Json(name = "description") val description: String,
        @Json(name = "url") val url: String
    )


}