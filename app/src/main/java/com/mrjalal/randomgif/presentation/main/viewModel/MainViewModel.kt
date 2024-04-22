package com.mrjalal.randomgif.presentation.main.viewModel

import androidx.lifecycle.viewModelScope
import com.mrjalal.randomgif.domain.usecase.FetchRandomGifUseCase
import com.mrjalal.randomgif.presentation.common.mapper.SingleGifDomainToUiModelMapper
import com.mrjalal.randomgif.presentation.common.model.DataState
import com.mrjalal.randomgif.presentation.common.model.SingleGifUiModel
import com.mrjalal.randomgif.presentation.common.viewModel.ApiExceptionHandler
import com.mrjalal.randomgif.presentation.common.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing the main screen's state and events.
 * @param fetchRandomGifUseCase The use case for fetching a random GIF.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchRandomGifUseCase: FetchRandomGifUseCase,
    private val singleGifMapper: SingleGifDomainToUiModelMapper,
    private val apiExceptionHandler: ApiExceptionHandler
): BaseViewModel(apiExceptionHandler), MainContract {
    // State flow representing the current state of the main screen.
    private val _state = MutableStateFlow(MainContract.State.EMPTY)
    override val state: StateFlow<MainContract.State> = _state.asStateFlow()

    // Channel for emitting effects to be observed by the UI.
    private val effectChannel = Channel<MainContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<MainContract.Effect> = effectChannel.receiveAsFlow()

    // Job for periodic fetching of random GIFs.
    private var periodicRandomGifJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        cancelPeriodicRandomGifJob()
    }

    /**
     * Process incoming events and update the ViewModel's state accordingly.
     * @param event The event to be processed.
     */
    override fun event(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnStartRandomGif -> {
                startPeriodicRandomGif()
            }
            is MainContract.Event.OnStopRandomGif -> {
                stopPeriodicRandomGif()
            }
            is MainContract.Event.OnSearchBarStatusChange -> {
                onSearchBarStatusChange(event.active)
            }
        }
    }

    /**
     * Update the search bar status based on the provided [active] parameter.
     * @param active Whether the search bar is active or not.
     */
    private fun onSearchBarStatusChange(active: Boolean) {
        updateSearchBarStatus(active)

        if (active) {
            stopPeriodicRandomGif()
        } else {
            startPeriodicRandomGif()
        }
    }

    /**
     * Update the search bar status in the ViewModel's state.
     * @param active Whether the search bar is active or not.
     */
    private fun updateSearchBarStatus(active: Boolean) {
        _state.update {
            it.copy(isSearchBarActive = active)
        }
    }

    /**
     * Start periodic fetching of random GIFs.
     */
    private fun startPeriodicRandomGif() {
        if (periodicRandomGifJob?.isActive == true) return

        periodicRandomGifJob = viewModelScope.launch {
            while (isActive) {
                fetchRandomGif()
                delay(10_000)
            }
        }
    }

    /**
     * Fetch a random GIF and update the ViewModel's state accordingly.
     */
    private suspend fun fetchRandomGif() {
        fetchRandomGifUseCase()
            .onSuccess { response ->
                updateRandomGifState(
                    gif = DataState.Success(singleGifMapper.map(response))
                )
            }
            .onFailure {error ->
                onApiCallException(error) {
                    updateRandomGifState(
                        gif = DataState.Error(it)
                    )
                    stopPeriodicRandomGif()
                }
            }
    }

    /**
     * Update the ViewModel's state with the provided [gif] state.
     * @param gif The state of the random GIF.
     */
    private fun updateRandomGifState(gif: DataState<SingleGifUiModel>) {
        _state.update {
            it.copy(
                randomGif = gif
            )
        }
    }

    /**
     * Stop the periodic fetching of random GIFs.
     */
    private fun stopPeriodicRandomGif() {
        cancelPeriodicRandomGifJob()
    }

    private fun cancelPeriodicRandomGifJob() {
        periodicRandomGifJob?.cancel()
    }
}