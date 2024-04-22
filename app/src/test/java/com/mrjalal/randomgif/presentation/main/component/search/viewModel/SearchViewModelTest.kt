package com.mrjalal.randomgif.presentation.main.component.search.viewModel

import com.mrjalal.randomgif.domain.repository.model.GifDomain
import com.mrjalal.randomgif.domain.repository.model.InquiryGifDomain
import com.mrjalal.randomgif.domain.usecase.InquiryGifsUseCase
import com.mrjalal.randomgif.presentation.MainDispatcherRule
import com.mrjalal.randomgif.presentation.common.mapper.InquiryGifDomainToUiModelMapper
import com.mrjalal.randomgif.presentation.common.model.DataState
import com.mrjalal.randomgif.presentation.common.model.GifUiModel
import com.mrjalal.randomgif.presentation.common.model.InquiryGifUiModel
import com.mrjalal.randomgif.presentation.common.viewModel.ApiExceptionHandler
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockInquiryGifsUseCase: InquiryGifsUseCase = mockk()

    private val apiExceptionHandler: ApiExceptionHandler = mockk()
    private val inquiryGifMapper: InquiryGifDomainToUiModelMapper = mockk()
    private var sut = SearchViewModel(
        inquiryGifsUseCase = mockInquiryGifsUseCase,
        apiExceptionHandler = apiExceptionHandler,
        inquiryGifMapper = inquiryGifMapper
    )

    private val gitDomain = GifDomain(
        gifPageUrl = "",
        url = "",
        previewUrl = "",
        id = "ID",
        rating = "G",
        title = "TITLE"
    )
    private val inquiryGifDomain = InquiryGifDomain(
        data = listOf(gitDomain).toImmutableList()
    )

    private val gitUiModel = GifUiModel(
        gifPageUrl = "",
        url = "",
        previewUrl = "",
        id = "ID",
        rating = "G",
        title = "TITLE"
    )
    private val inquiryGifUiModel = InquiryGifUiModel(
        data = listOf(gitUiModel).toImmutableList()
    )

    @Test
    fun `onQueryChanged event should fetch gifs when query length is greater than or equal to 2`() =
        runTest {
            // Given
            val query = "test"
            val expectedResult = DataState.Success(inquiryGifUiModel)
            every { inquiryGifMapper.map(inquiryGifDomain) } returns inquiryGifUiModel
            coEvery { mockInquiryGifsUseCase(query) } returns Result.success(inquiryGifDomain)

            // When
            sut.event(SearchContract.Event.OnQueryChanged(query))

            // Then
            assertEquals(expectedResult, sut.state.value.searchResult)
        }

    @Test
    fun `onQueryChanged event should not fetch gifs when query length is less than 2`() = runTest {
        // Given
        val query = "a" // Length less than 2
        every { inquiryGifMapper.map(inquiryGifDomain) } returns inquiryGifUiModel
        coEvery { mockInquiryGifsUseCase(query) } returns Result.success(inquiryGifDomain)

        // When
        sut.event(SearchContract.Event.OnQueryChanged(query))

        // Then
        coVerify(exactly = 0) { mockInquiryGifsUseCase(query) }
    }

    @Test
    fun `onTryAgain event should fetch gifs`() = runTest {
        // Given
        val query = "test"
        val expectedResult = DataState.Success(inquiryGifUiModel)
        every { inquiryGifMapper.map(inquiryGifDomain) } returns inquiryGifUiModel
        coEvery { mockInquiryGifsUseCase(query) } returns Result.success(inquiryGifDomain)
        sut.event(SearchContract.Event.OnQueryChanged(query))

        // When
        sut.event(SearchContract.Event.OnTryAgain)

        // Then
        assertEquals(expectedResult, sut.state.value.searchResult)
    }

    @Test
    fun `resetSearchQuery event should update search query to empty`() = runTest {
        // Given
        val initialQuery = "test"
        every { inquiryGifMapper.map(inquiryGifDomain) } returns inquiryGifUiModel
        coEvery { mockInquiryGifsUseCase(initialQuery) } returns Result.success(inquiryGifDomain)
        sut.event(SearchContract.Event.OnQueryChanged(initialQuery))

        // When
        sut.event(SearchContract.Event.ResetSearchQuery)

        // Then
        assertEquals("", sut.state.value.query)
    }
}