package com.mrjalal.randomgif.presentation.detail.viewModel

import com.mrjalal.randomgif.presentation.common.helper.UnidirectionalViewModel
import com.mrjalal.randomgif.presentation.common.model.DataState
import com.mrjalal.randomgif.presentation.common.model.SingleGifUiModel

interface DetailContract :
    UnidirectionalViewModel<DetailContract.Event, DetailContract.Effect, DetailContract.State> {
    sealed interface Event {
        data class FetchGifDetail(val id: String) : Event
    }

    sealed interface Effect
    data class State(
        val gifDetail: DataState<SingleGifUiModel>
    ) {
        companion object {
            val EMPTY = State(
                gifDetail = DataState.Loading
            )
        }
    }
}