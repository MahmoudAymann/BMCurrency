package com.bm.currency.core.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BaseStateViewModel<State : UiState>(uiState: State) : BaseViewModel() {

    private val _uiState = MutableStateFlow(uiState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    fun produce(uiState: State) {
        viewModelScope.launch {
            _uiState.emit(uiState)
        }
    }


}