package com.mrjalal.randomgif.presentation.main.component.search.viewModel

import androidx.lifecycle.viewModelScope
import com.mrjalal.randomgif.domain.usecase.InquiryGifsUseCase
import com.mrjalal.randomgif.presentation.common.mapper.InquiryGifDomainToUiModelMapper
import com.mrjalal.randomgif.presentation.common.model.DataState
import com.mrjalal.randomgif.presentation.common.model.InquiryGifUiModel
import com.mrjalal.randomgif.presentation.common.viewModel.ApiExceptionHandler
import com.mrjalal.randomgif.presentation.common.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing search functionality and state.
 * @param inquiryGifsUseCase The use case for querying GIFs based on a search query.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val inquiryGifsUseCase: InquiryGifsUseCase,
    private val inquiryGifMapper: InquiryGifDomainToUiModelMapper,
    private val apiExceptionHandler: ApiExceptionHandler
) : BaseViewModel(apiExceptionHandler), SearchContract {

    private val _state = MutableStateFlow(SearchContract.State.EMPTY)
    override val state: StateFlow<SearchContract.State> = _state.asStateFlow()

    private val effectChannel = Channel<SearchContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<SearchContract.Effect> = effectChannel.receiveAsFlow()

    private var searchResultJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        searchResultJob?.cancel()
    }

    override fun event(event: SearchContract.Event) {
        when (event) {
            is SearchContract.Event.OnQueryChanged -> {
                val query = event.query.take(MAX_QUERY_LENGTH) // Truncate the query if it exceeds max length
                updateSearchQuery(query)
                if (query.length >= 2) {
                    inquiryGifs(query)
                } else {
                    // Reset search result if query length is less than 2
                    updateSearchResult(DataState.Empty)
                }
            }
            is SearchContract.Event.OnTryAgain -> {
                val query = _state.value.query
                if (query.isNotEmpty()) {
                    inquiryGifs(query)
                }
            }
            is SearchContract.Event.ResetSearchQuery -> {
                updateSearchQuery("")
                updateSearchResult(DataState.Empty)
            }
        }
    }

    /**
     * Initiate a search query based on the provided [query].
     * @param query The search query.
     */
    private fun inquiryGifs(query: String) {
        searchResultJob?.cancel()
        searchResultJob = viewModelScope.launch {
            updateSearchResult(
                result = DataState.Loading
            )

            inquiryGifsUseCase(query)
                .onSuccess { response ->
                    updateSearchResult(
                        result = DataState.Success(inquiryGifMapper.map(response))
                    )
                }
                .onFailure { error ->
                    onApiCallException(error) {
                        updateSearchResult(
                            result = DataState.Error(it)
                        )
                    }
                }
        }
    }

    /**
     * Update the ViewModel's state with the provided search [result].
     * @param result The search result state.
     */
    private fun updateSearchResult(result: DataState<InquiryGifUiModel>) {
        _state.update {
            it.copy(
                searchResult = result
            )
        }
    }

    /**
     * Update the ViewModel's search query with the provided [query].
     * @param query The search query to update.
     */
    private fun updateSearchQuery(query:String) {
        _state.update {
            it.copy(query = query)
        }
    }

    companion object {
        const val MAX_QUERY_LENGTH = 15
    }
}