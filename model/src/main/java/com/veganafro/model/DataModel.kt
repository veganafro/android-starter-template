package com.veganafro.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataModel(
    @Json(name = "results")
    val results: List<Result>
) {

    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "key")
        val value: String
    )
}
