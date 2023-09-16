package com.bm.currency.features.fragment.convertcurrency.ui

import androidx.annotation.StringRes
import com.bm.currency.core.viewmodel.UiState

/**
 * Created by Mahmoud Ayman on 15/09/2023.
 * Email: mahmoud_aymann@outlook.com.
 */
data class ConvertCurrencyState(
    val amount: Float,
    val rate:Float=0f,
    val errorMessage: String? = null,
    @StringRes val errorMessageRes: Int?=null
) : UiState