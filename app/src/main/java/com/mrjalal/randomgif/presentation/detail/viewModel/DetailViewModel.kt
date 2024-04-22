package com.mrjalal.randomgif.presentation.detail.viewModel

import androidx.lifecycle.viewModelScope
import com.mrjalal.randomgif.domain.usecase.FetchSingleGifUseCase
import com.mrjalal.randomgif.presentation.common.mapper.SingleGifDomainToUiModelMapper
import com.mrjalal.randomgif.presentation.common.model.DataState
import com.mrjalal.randomgif.presentation.common.model.SingleGifUiModel
import com.mrjalal.randomgif.presentation.common.viewModel.ApiExceptionHandler
import com.mrjalal.randomgif.presentation.common.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
 * ViewModel responsible for managing details of a single GIF.
 * @param fetchSingleGifUseCase The use case for fetching details of a single GIF.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val fetchSingleGifUseCase: FetchSingleGifUseCase,
    private val singleGifMapper: SingleGifDomainToUiModelMapper,
    private val apiExceptionHandler: ApiExceptionHandler
) : BaseViewModel(apiExceptionHandler), DetailContract {

    private val _state = MutableStateFlow(DetailContract.State.EMPTY)
    override val state: StateFlow<DetailContract.State> = _state.asStateFlow()

    private val effectChannel = Channel<DetailContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<DetailContract.Effect> = effectChannel.receiveAsFlow()

    override fun event(event: DetailContract.Event) {
        when (event) {
            is DetailContract.Event.FetchGifDetail -> {
                fetchGifDetailById(event.id)
            }
        }
    }

    /**
     * Fetch details of a GIF by its [id].
     * @param id The ID of the GIF to fetch details for.
     */
    private fun fetchGifDetailById(id: String) {
        viewModelScope.launch {
            fetchSingleGifUseCase(id)
                .onSuccess { response ->
                    updateGifDetail(
                        result = DataState.Success(singleGifMapper.map(response))
                    )
                }
                .onFailure { error ->
                    onApiCallException(error) {
                        updateGifDetail(
                            result = DataState.Error(it)
                        )
                    }
                }
        }
    }

    /**
     * Update the ViewModel's state with the details of the fetched GIF.
     * @param result The result state of the fetch operation.
     */
    private fun updateGifDetail(result: DataState<SingleGifUiModel>) {
        _state.update {
            it.copy(
                gifDetail = result
            )
        }
    }
}