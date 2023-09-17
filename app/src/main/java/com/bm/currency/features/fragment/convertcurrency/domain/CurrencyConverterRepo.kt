package com.bm.currency.features.fragment.convertcurrency.domain

import com.bm.currency.core.network.CurrencyApiService
import com.bm.currency.features.fragment.convertcurrency.data.CurrencyRateResponse
import javax.inject.Inject

interface CurrencyConverterRepo{
    suspend fun getCurrencyRates(queryMap: HashMap<String, String?>): CurrencyRateResponse
}