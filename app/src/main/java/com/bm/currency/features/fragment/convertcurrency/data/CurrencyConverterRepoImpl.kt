package com.bm.currency.features.fragment.convertcurrency.data

import com.bm.currency.core.network.CurrencyApiService
import com.bm.currency.features.fragment.convertcurrency.domain.CurrencyConverterRepo
import javax.inject.Inject

class CurrencyConverterRepoImpl @Inject constructor(private val api: CurrencyApiService) :
    CurrencyConverterRepo {
    override suspend fun getCurrencyRates(queryMap: HashMap<String, String?>): CurrencyRateResponse {
        return api.getCurrencyRates(queryMap)
    }
}