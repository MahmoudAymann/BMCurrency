package com.bm.currency.core.network

import com.bm.currency.BuildConfig
import com.bm.currency.features.fragment.convertcurrency.data.CurrencyRateResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CurrencyApiService {

    companion object {
        const val LATEST = "latest?access_key=${BuildConfig.FixerApiKey}"
    }

    @GET(LATEST)
    suspend fun getCurrencyRates(@QueryMap queryMap: HashMap<String, String?>): CurrencyRateResponse


}