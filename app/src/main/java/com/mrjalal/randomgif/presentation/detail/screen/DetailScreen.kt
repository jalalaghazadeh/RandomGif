package com.mrjalal.randomgif.presentation.detail.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrjalal.randomgif.R
import com.mrjalal.randomgif.presentation.common.component.TopBar
import com.mrjalal.randomgif.presentation.common.component.gifDetail.GifDetail
import com.mrjalal.randomgif.presentation.common.helper.use
import com.mrjalal.randomgif.presentation.common.model.DataState
import com.mrjalal.randomgif.presentation.common.model.SingleGifUiModel
import com.mrjalal.randomgif.presentation.detail.viewModel.DetailContract
import com.mrjalal.randomgif.presentation.detail.viewModel.DetailViewModel

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onBack: () -> Boolean,
    id: String?,
) {
    val (state, _, dispatcher) = use(viewModel)

    LaunchedEffect(id) {
        id?.let { dispatcher(DetailContract.Event.FetchGifDetail(it)) }
    }

    BackHandler {
        onBack()
    }

    Scaffold(
        topBar = {
            TopBar(
                title = if (state.gifDetail is DataState.Success) {
                    state.gifDetail.data.data.title
                } else {
                    stringResource(id = R.string.gif_title)
                },
                onBack = onBack
            )

        },
        containerColor = Color.White
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(top = 8.dp)
        ) {
            DetailScreenContent(
                item = state.gifDetail,
                onTryAgain = {
                    id?.let { id -> dispatcher(DetailContract.Event.FetchGifDetail(id)) }
                }
            )
        }
    }
}

@Composable
fun DetailScreenContent(
    item: DataState<SingleGifUiModel>,
    onTryAgain: () -> Unit
) {
    GifDetail(
        item = item,
        onTryAgain = onTryAgain
    )
}
