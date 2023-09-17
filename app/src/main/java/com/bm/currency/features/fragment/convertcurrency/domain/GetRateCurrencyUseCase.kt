package com.bm.currency.features.fragment.convertcurrency.domain

import com.bm.currency.core.extensions.toHashMapParams
import com.bm.currency.core.usecase.BaseUseCase
import com.bm.currency.features.fragment.convertcurrency.data.ConvertCurrencyParam
import com.bm.currency.features.fragment.convertcurrency.data.CurrencyRateResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRateCurrencyUseCase @Inject constructor(private val currencyConverterRepo: CurrencyConverterRepo) :
    BaseUseCase<CurrencyRateResponse, CurrencyRateResponse, ConvertCurrencyParam>() {
    override fun mapper(req: CurrencyRateResponse): CurrencyRateResponse {
        return req
    }

    override fun executeRemote(params: ConvertCurrencyParam?): Flow<CurrencyRateResponse> {
        return flow { emit(currencyConverterRepo.getCurrencyRates(params.toHashMapParams()!!)) }
    }
}