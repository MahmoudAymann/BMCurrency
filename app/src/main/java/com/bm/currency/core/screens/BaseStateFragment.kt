package com.bm.currency.core.screens

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.bm.currency.core.extensions.InflateFragment
import com.bm.currency.core.viewmodel.BaseStateViewModel
import com.bm.currency.core.viewmodel.UiState
import kotlinx.coroutines.launch

abstract class BaseStateFragment<B : ViewBinding, State : UiState>(inflate: InflateFragment<B>) :
    BaseFragment<B>(inflate) {

    abstract val viewModel: BaseStateViewModel<State>
    abstract fun onCollectUiState(uiState: State)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectUiState()
    }

    private fun collectUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                onCollectUiState(uiState)
            }
        }
    }

}