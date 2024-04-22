package com.mrjalal.randomgif.presentation.common.viewModel

import androidx.lifecycle.ViewModel

open class BaseViewModel(
    private val apiExceptionHandler: ApiExceptionHandler
): ViewModel() {
    fun onApiCallException(
        ex:Throwable,
        callback: (String) -> Unit
    ) {
        val errorMessageType = apiExceptionHandler.handleException(ex)
        callback(errorMessageType)
    }
}