package com.bm.currency.features.fragment.convertcurrency.ui

import com.bm.currency.core.viewmodel.UiAction

/**
 * Created by Mahmoud Ayman on 15/09/2023.
 * Email: mahmoud_aymann@outlook.com.
 */
sealed class ConvertCurrencyAction : UiAction {

    data class GetCurrencyRate(val from: String?, val to: String?, val amount: String) : ConvertCurrencyAction()
    data class Calculate(val from: String?, val to: String?, val amount: String?) : ConvertCurrencyAction()
}