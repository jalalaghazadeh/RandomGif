package com.mrjalal.randomgif.presentation.detail.viewModel

import com.mrjalal.randomgif.domain.repository.model.GifDomain
import com.mrjalal.randomgif.domain.repository.model.SingleGifDomain
import com.mrjalal.randomgif.domain.usecase.FetchSingleGifUseCase
import com.mrjalal.randomgif.presentation.MainDispatcherRule
import com.mrjalal.randomgif.presentation.common.mapper.SingleGifDomainToUiModelMapper
import com.mrjalal.randomgif.presentation.common.model.DataState
import com.mrjalal.randomgif.presentation.common.model.GifUiModel
import com.mrjalal.randomgif.presentation.common.model.SingleGifUiModel
import com.mrjalal.randomgif.presentation.common.viewModel.ApiExceptionHandler
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockFetchSingleGifUseCase: FetchSingleGifUseCase = mockk()
    private val apiExceptionHandler: ApiExceptionHandler = mockk()
    private val singleGifMapper: SingleGifDomainToUiModelMapper = mockk()
    private val viewModel = DetailViewModel(
        fetchSingleGifUseCase = mockFetchSingleGifUseCase,
        apiExceptionHandler = apiExceptionHandler,
        singleGifMapper = singleGifMapper
    )

    private val gitUiModel = GifUiModel(
        gifPageUrl = "",
        url = "",
        previewUrl = "",
        id = "ID",
        rating = "G",
        title = "TITLE"
    )
    private val singleGifUiModel = SingleGifUiModel(
        data = gitUiModel
    )

    private val gitDomain = GifDomain(
        gifPageUrl = "",
        url = "",
        previewUrl = "",
        id = "ID",
        rating = "G",
        title = "TITLE"
    )
    private val singleGifDomain = SingleGifDomain(
        data = gitDomain
    )

    private val gifId = "gifId"


    @Test
    fun `event should fetch gif detail successfully`() = runTest {
        // Given
        val gifDetail = singleGifDomain
        val expectedState = DetailContract.State(gifDetail = DataState.Success(singleGifUiModel))

        every { singleGifMapper.map(singleGifDomain) } returns singleGifUiModel
        coEvery { mockFetchSingleGifUseCase(gifId) } returns Result.success(gifDetail)

        // When
        viewModel.event(DetailContract.Event.FetchGifDetail(gifId))

        // Then
        assertEquals(expectedState, viewModel.state.value)
    }
}