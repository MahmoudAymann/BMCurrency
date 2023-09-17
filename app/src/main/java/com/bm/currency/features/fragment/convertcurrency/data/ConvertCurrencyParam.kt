package com.bm.currency.features.fragment.convertcurrency.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Mahmoud Ayman on 15/09/2023.
 * Email: mahmoud_aymann@outlook.com.
 */
@JsonClass(generateAdapter = true)
data class ConvertCurrencyParam(
    @Json(name="base")
    var base: String? = null,
    @Json(name="symbols")
    var symbols: String? = null)