package com.mrjalal.randomgif.presentation.common.component.gifDetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mrjalal.randomgif.R
import com.mrjalal.randomgif.presentation.common.component.Loading
import com.mrjalal.randomgif.presentation.common.component.TryAgain
import com.mrjalal.randomgif.presentation.common.model.DataState
import com.mrjalal.randomgif.presentation.common.model.SingleGifUiModel

@Composable
fun GifDetail(
    item: DataState<SingleGifUiModel>,
    onTryAgain: () -> Unit
) {
    when (item) {
        is DataState.Success -> {
            GifDetailContent(
                item = item.data.data,
                title = stringResource(id = R.string.random_selected_gif)
            )
        }

        is DataState.Error -> {
            TryAgain(
                errorType = item.errorMessage,
                onTryAgain = onTryAgain
            )
        }

        is DataState.Loading -> {
            Loading()
        }

        is DataState.Empty -> {}
    }
}