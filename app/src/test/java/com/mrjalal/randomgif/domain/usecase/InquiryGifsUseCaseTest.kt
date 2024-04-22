package com.mrjalal.randomgif.domain.usecase

import com.mrjalal.randomgif.domain.repository.GifRepository
import com.mrjalal.randomgif.domain.repository.model.GifDomain
import com.mrjalal.randomgif.domain.repository.model.InquiryGifDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import java.lang.Exception

class InquiryGifsUseCaseTest {

    private val repository: GifRepository = mockk()
    private val sut = InquiryGifsUseCase(repository)

    private val gitUiModel = GifDomain(
        gifPageUrl = "",
        url = "",
        previewUrl = "",
        id = "ID",
        rating = "G",
        title = "TITLE"
    )
    private val inquiryGifUiModel = InquiryGifDomain(
        data = listOf(gitUiModel).toImmutableList()
    )
    private val query = "test"

    @Test
    fun `fetch-single-gif-use-case return success`() {
        // Given
        val expectedResult = Result.success(inquiryGifUiModel)
        coEvery { repository.inquiryGifs(query) } returns expectedResult

        // When
        val result = runBlocking { sut(query) }

        // Then
        assertEquals(expectedResult, result)
    }

    @Test
    fun `fetch-single-gif-use-case return error`() {
        // Given
        val errorMessage = "ERROR"
        val expectedError = Result.failure<InquiryGifDomain>(Exception(errorMessage))
        coEvery { repository.inquiryGifs(query) } returns expectedError

        // When
        val result = runBlocking { sut(query) }

        // Then
        assertEquals(false, result.isSuccess)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
}