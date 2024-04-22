package com.mrjalal.randomgif.presentation.common.viewModel

import com.mrjalal.randomgif.presentation.common.model.NetworkErrorMessageType
import java.io.IOException
import javax.inject.Inject

class ApiExceptionHandler @Inject constructor() {
    fun handleException(ex: Throwable): String {
        return when (ex) {
            is IOException -> NetworkErrorMessageType.SERVER_ERROR.name
            else -> NetworkErrorMessageType.GENERAL.name
        }
    }
}


