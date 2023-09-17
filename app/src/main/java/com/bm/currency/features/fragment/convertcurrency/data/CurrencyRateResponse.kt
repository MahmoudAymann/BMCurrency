package com.bm.currency.features.fragment.convertcurrency.data

import com.bm.currency.core.network.BaseResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyRateResponse(

    @Json(name = "date")
    val date: String? = null,

    @Json(name = "rates")
    val rates: Rates? = null,

    @Json(name = "timestamp")
    val timestamp: Int? = null,

    @Json(name = "base")
    val base: String? = null
) : BaseResponse()

@JsonClass(generateAdapter = true)
data class Rates(
    @Json(name = "EGP")
    val egp: Float? = null,
    @Json(name = "EUR")
    val eur: Float? = null,
    @Json(name = "USD")
    val usd: Float? = null,
    @Json(name = "AED")
    val aed: Float? = null
)