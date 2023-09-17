package com.bm.currency.features.fragment.convertcurrency.data

import com.bm.currency.core.network.CurrencyApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CurrencyConverterRepoImplTest {

    private lateinit var repo: CurrencyConverterRepoImpl

    @Mock
    private lateinit var api: CurrencyApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repo = CurrencyConverterRepoImpl(api)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getCurrencyRates should call the API service`() = runTest {
        // Given
        val queryMap = HashMap<String, String?>()
        val currencyRateResponse = CurrencyRateResponse()

        // Mock the API service's behavior
        Mockito.`when`(api.getCurrencyRates(queryMap)).thenReturn(currencyRateResponse)

        // When
        val result = repo.getCurrencyRates(queryMap)
        //Then
        Mockito.verify(api).getCurrencyRates(queryMap)
        assert(result == currencyRateResponse)
    }
}