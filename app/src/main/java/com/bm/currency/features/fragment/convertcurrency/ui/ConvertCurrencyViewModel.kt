package com.bm.currency.features.fragment.convertcurrency.ui

import androidx.lifecycle.viewModelScope
import com.bm.currency.core.network.Resource
import com.bm.currency.core.viewmodel.BaseStateViewModel
import com.bm.currency.features.fragment.convertcurrency.data.ConvertCurrencyParam
import com.bm.currency.features.fragment.convertcurrency.domain.GetRateCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mahmoud Ayman on 15/09/2023.
 * Email: mahmoud_aymann@outlook.com.
 */
@HiltViewModel
class ConvertCurrencyViewModel @Inject constructor(private val getRateCurrencyUseCase: GetRateCurrencyUseCase) :
    BaseStateViewModel<ConvertCurrencyState, ConvertCurrencyAction>(ConvertCurrencyState(amount = 1f)) {

    private val params: ConvertCurrencyParam = ConvertCurrencyParam()
    override fun onActionReceived(action: ConvertCurrencyAction) {
        when (action) {
            is ConvertCurrencyAction.GetCurrencyRate -> {
                if (isValidCurrency(
                        from = action.from, to = action.to, amount = action.amount
                    )
                ) getRateFrom(
                    from = action.from!!,
                    to = action.to!!,
                    amount = action.amount.toFloatOrNull() ?: 1f
                )
            }

            is ConvertCurrencyAction.Calculate -> {

                if (isValidCurrency(action.from, action.to, action.amount)) produce(
                    uiState.value.copy(
                        amount = calculateAmount(
                            amount = action.amount?.toFloat() ?: 1f, rate = uiState.value.rate
                        )
                    )
                )
            }
        }
    }

    private fun isValidCurrency(from: String?, to: String?, amount: String?): Boolean {
        return !amount.isNullOrBlank() && !from.isNullOrBlank() && !to.isNullOrBlank()
    }


    private fun getRateFrom(from: String, to: String, amount: Float) {
        //after get rate from API
        params.base = from
        params.symbols = to
        getRateCurrencyUseCase.invoke(viewModelScope, params) {
            when (it) {
                is Resource.Failure -> produce(uiState.value.copy(errorMessage = it.message))
                is Resource.Progress -> produce(uiState.value.copy(loading = it.loading))
                is Resource.Success -> {
                    val rate: Float = when (to) {
                        "EGP" -> it.data.rates?.egp
                        "USD" -> it.data.rates?.usd
                        "EUR" -> it.data.rates?.eur
                        "AED" -> it.data.rates?.aed
                        else -> 0f
                    } ?: 0f
                    produce(
                        uiState.value.copy(
                            amount = calculateAmount(amount = amount, rate = rate),
                            rate = rate
                        )
                    )
                }
            }
        }
    }


    private fun calculateAmount(amount: Float, rate: Float): Float {
        return amount * rate
    }

}