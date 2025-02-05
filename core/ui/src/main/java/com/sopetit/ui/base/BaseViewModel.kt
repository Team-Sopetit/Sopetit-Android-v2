package com.sopetit.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel(
    initialState: PageState
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    protected fun updateState(state: PageState) {
        viewModelScope.launch {
            _uiState.update { state }
        }
    }

    protected fun emitEventFlow(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    protected fun <D> resultResponse(
        response: Result<D>,
        successCallback: (D) -> Unit,
        errorCallback: ((Throwable) -> Unit)? = null,
    ) {
        viewModelScope.launch {
            response.fold(
                onSuccess = { data ->
                    successCallback.invoke(data)
                },
                onFailure = { exception ->
                    Timber.e("[에러 발생]", exception.stackTraceToString())
                    errorCallback?.invoke(exception)
                }
            )
        }
    }
}