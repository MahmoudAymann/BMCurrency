package com.bm.currency.core.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseStateViewModel<State : UiState, Action : UiAction>(uiState: State) :
    BaseViewModel() {

    private val _uiState = MutableStateFlow(uiState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    fun produce(uiState: State) {
        viewModelScope.launch {
            _uiState.emit(uiState)
        }
    }

    protected abstract fun onActionReceived(action: Action)

    fun setAction(action: Action){
        onActionReceived(action)
    }

}