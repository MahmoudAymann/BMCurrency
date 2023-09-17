package com.bm.currency.core.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
open class BaseResponse(
    @Json(name = "success")
    val isSuccess: Boolean? = null,
    val error: ErrorResponse? = null
) {
    @JsonClass(generateAdapter = true)
    data class ErrorResponse(
        val code: Int? = null,
        @Json(name = "info")
        val message: String? = null
    )
}