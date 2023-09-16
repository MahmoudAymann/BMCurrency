package com.bm.currency.features.fragment.convertcurrency.ui

import com.bm.currency.core.viewmodel.BaseStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mahmoud Ayman on 15/09/2023.
 * Email: mahmoud_aymann@outlook.com.
 */
@HiltViewModel
class ConvertCurrencyViewModel @Inject constructor() :
    BaseStateViewModel<ConvertCurrencyState, ConvertCurrencyAction>(ConvertCurrencyState(amount = 1f)) {

    //   private val params: ConvertCurrencyParam = ConvertCurrencyParam()
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
        val rate = 30f
        produce(
            uiState.value.copy(amount = calculateAmount(amount = amount, rate = rate), rate = rate)
        )
    }


    private fun calculateAmount(amount: Float, rate: Float): Float {
        return amount * rate
    }

}