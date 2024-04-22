package com.mrjalal.randomgif.presentation.main.viewModel

import com.mrjalal.randomgif.domain.usecase.FetchRandomGifUseCase
import com.mrjalal.randomgif.presentation.MainDispatcherRule
import com.mrjalal.randomgif.presentation.common.mapper.SingleGifDomainToUiModelMapper
import com.mrjalal.randomgif.presentation.common.viewModel.ApiExceptionHandler
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainViewModelTest{
    @get:Rule
    val mainDispatcherRule= MainDispatcherRule()

    private val fetchRandomGifUseCase: FetchRandomGifUseCase = mockk()
    private val apiExceptionHandler: ApiExceptionHandler = mockk()
    private val singleGifDomainToUiModelMapper: SingleGifDomainToUiModelMapper = mockk()
    private var sut = MainViewModel(
        fetchRandomGifUseCase = fetchRandomGifUseCase,
        singleGifMapper = singleGifDomainToUiModelMapper,
        apiExceptionHandler = apiExceptionHandler
    )

    @Test
    fun `calling OnSearchBarStatusChange event with true should make isSearchBarActive of state to be true`() = runTest {
        // When
        sut.event(MainContract.Event.OnSearchBarStatusChange(active = true))

        // Then
        assertEquals(true, sut.state.value.isSearchBarActive)
    }
}