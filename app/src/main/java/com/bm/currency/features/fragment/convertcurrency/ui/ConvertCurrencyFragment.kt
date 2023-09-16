package com.bm.currency.features.fragment.convertcurrency.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.bm.currency.core.extensions.asAutoCompleteSetListToAdapter
import com.bm.currency.core.extensions.setDefaultTextWhenUnFocused
import com.bm.currency.core.screens.BaseStateFragment
import com.bm.currency.databinding.FragmentConvertCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mahmoud Ayman on 15/09/2023.
 * Email: mahmoud_aymann@outlook.com.
 */
@AndroidEntryPoint
class ConvertCurrencyFragment :
    BaseStateFragment<FragmentConvertCurrencyBinding, ConvertCurrencyState, ConvertCurrencyAction>(
        FragmentConvertCurrencyBinding::inflate
    ) {

    override val viewModel: ConvertCurrencyViewModel by viewModels()

    private var fromCurrency = ""
    private var toCurrency = ""
    private var amount = "1"
    private var result = ""

    private val etFromCurrency by lazy { binding.tilBaseCurrency.editText!! }
    private val etToCurrency by lazy { binding.tilConvertToCurrency.editText!! }
    private val etAmount by lazy { binding.tilAmount.editText!! }

    companion object {
        private const val FROM_CURRENCY_BUNDLE_KEY = "fromCurrency"
        private const val TO_CURRENCY_BUNDLE_KEY = "toCurrency"
        private const val AMOUNT_BUNDLE_KEY = "amount"
        private const val RESULT_BUNDLE_KEY = "result"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            fromCurrency = savedInstanceState.getString(FROM_CURRENCY_BUNDLE_KEY, "")
            toCurrency = savedInstanceState.getString(TO_CURRENCY_BUNDLE_KEY, "")
            amount = savedInstanceState.getString(AMOUNT_BUNDLE_KEY, "1")
            result = savedInstanceState.getString(RESULT_BUNDLE_KEY, "")
        }
        setCurrencies()
        initUI()
        if (savedInstanceState != null) {
            viewModel.setAction(
                ConvertCurrencyAction.Calculate(
                    fromCurrency, toCurrency, amount = amount
                )
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(
            FROM_CURRENCY_BUNDLE_KEY, etFromCurrency.text?.toString()
        )
        outState.putString(
            TO_CURRENCY_BUNDLE_KEY, etToCurrency.text?.toString()
        )
        outState.putString(AMOUNT_BUNDLE_KEY, etAmount.text?.toString())
        outState.putString(RESULT_BUNDLE_KEY, binding.tilOutput.editText?.text?.toString())
        super.onSaveInstanceState(outState)
    }

    private fun initUI() {
        with(binding) {
            tilAmount.editText?.setDefaultTextWhenUnFocused("1") {
                val from = binding.tilBaseCurrency.editText?.text?.toString()
                val to = binding.tilOutput.editText?.text?.toString()
                viewModel.setAction(
                    ConvertCurrencyAction.Calculate(
                        from, to, amount = tilAmount.editText?.text?.toString()
                    )
                )
            }
            tilAmount.editText?.doAfterTextChanged {
                val from = binding.tilBaseCurrency.editText?.text?.toString()
                val to = binding.tilConvertToCurrency.editText?.text?.toString()
                if (tilAmount.editText?.isFocused == true) {
                    viewModel.setAction(
                        ConvertCurrencyAction.Calculate(
                            from, to, amount = it?.toString()
                        )
                    )
                }
            }
            tilAmount.editText?.setText(amount)
        }
    }

    private fun setCurrencies() {
        with(binding) {
            tilBaseCurrency.editText.asAutoCompleteSetListToAdapter(context = requireContext(),
                list = listOf("EUR", "EGP", "USD"),
                onItemSelected = {
                    setAction(
                        ConvertCurrencyAction.GetCurrencyRate(
                            from = it,
                            to = tilConvertToCurrency.editText?.text?.toString(),
                            amount = tilAmount.editText?.text?.toString() ?: "1"
                        )
                    )
                })
            tilBaseCurrency.editText?.setText(fromCurrency)
            tilConvertToCurrency.editText.asAutoCompleteSetListToAdapter(context = requireContext(),
                list = listOf("EUR", "EGP", "USD"),
                onItemSelected = {
                    setAction(
                        ConvertCurrencyAction.GetCurrencyRate(
                            from = tilBaseCurrency.editText?.text?.toString(),
                            to = it,
                            amount = tilAmount.editText?.text?.toString() ?: "1"
                        )
                    )
                })
            tilConvertToCurrency.editText?.setText(toCurrency)
        }
    }

    private fun isValidCurrency(): Boolean {
        val fromValid = !binding.tilBaseCurrency.editText?.text.isNullOrBlank()
        val toValid = !binding.tilConvertToCurrency.editText?.text.isNullOrBlank()
        return fromValid && toValid
    }

    override fun onCollectUiState(uiState: ConvertCurrencyState) {
        if (isValidCurrency())
            binding.tilOutput.editText?.setText(uiState.amount.toString())
        if (uiState.errorMessageRes != null) showToast(uiState.errorMessageRes)
        if (uiState.errorMessage != null) showToast(uiState.errorMessage)
    }

}