package com.mrjalal.randomgif.presentation.main.viewModel

import com.mrjalal.randomgif.presentation.common.helper.UnidirectionalViewModel
import com.mrjalal.randomgif.presentation.common.model.DataState
import com.mrjalal.randomgif.presentation.common.model.SingleGifUiModel

interface MainContract:
    UnidirectionalViewModel<MainContract.Event, MainContract.Effect, MainContract.State> {
    sealed interface Event {
        data object OnStartRandomGif: Event
        data object OnStopRandomGif: Event
        data class OnSearchBarStatusChange(val active: Boolean): Event
    }
    sealed interface Effect
    data class State(
        val randomGif: DataState<SingleGifUiModel>,
        val isSearchBarActive: Boolean

    ) {
        companion object {
            val EMPTY = State(
                randomGif = DataState.Loading,
                isSearchBarActive = false
            )
        }
    }
}