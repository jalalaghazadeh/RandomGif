package com.mrjalal.randomgif.presentation.main.component.search.viewModel

import com.mrjalal.randomgif.presentation.common.helper.UnidirectionalViewModel
import com.mrjalal.randomgif.presentation.common.model.DataState
import com.mrjalal.randomgif.presentation.common.model.InquiryGifUiModel

interface SearchContract :
    UnidirectionalViewModel<SearchContract.Event, SearchContract.Effect, SearchContract.State> {
    sealed interface Event{
        data class OnQueryChanged(val query: String): Event
        data object ResetSearchQuery : Event
        data object OnTryAgain : Event
    }
    sealed interface Effect

    data class State(
        val query: String,
        val searchResult: DataState<InquiryGifUiModel>
    ) {
        companion object {
            val EMPTY = State(
                query = "",
                searchResult = DataState.Empty
            )
        }
    }
}