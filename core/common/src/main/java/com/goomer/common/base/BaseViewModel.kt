package com.goomer.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel<T> : ViewModel() {
    protected val _uiState = MutableStateFlow<UiState<T>>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    protected fun setLoading() = _uiState.tryEmit(UiState.Loading)
    protected fun setSuccess(data: T) = _uiState.tryEmit(UiState.Success(data))
    protected fun setError() = _uiState.tryEmit(UiState.Error)
}

