package com.bm.currency.features.fragment.convertcurrency.domain

import com.bm.currency.core.extensions.toHashMapParams
import com.bm.currency.features.fragment.convertcurrency.data.ConvertCurrencyParam
import com.bm.currency.features.fragment.convertcurrency.data.CurrencyRateResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetRateCurrencyUseCaseTest {

    private lateinit var useCase: GetRateCurrencyUseCase

    @Mock
    private lateinit var currencyConverterRepo: CurrencyConverterRepo

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetRateCurrencyUseCase(currencyConverterRepo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `executeRemote should call CurrencyConverterRepo and emit its response`() = runTest {
        // Given
        val params = ConvertCurrencyParam(base = "EGP", symbols = "USD")
        val expectedResponse = CurrencyRateResponse()

        Mockito.`when`(currencyConverterRepo.getCurrencyRates(params.toHashMapParams()!!))
            .thenReturn(expectedResponse)

        // When
        val resultFlow = useCase.executeRemote(params)
        // Then
        resultFlow.collect { result ->
            assert(result == expectedResponse)
            Mockito.verify(currencyConverterRepo).getCurrencyRates(params.toHashMapParams()!!)
        }
    }
}