package com.mrjalal.randomgif.domain.usecase

import com.mrjalal.randomgif.domain.repository.GifRepository
import com.mrjalal.randomgif.domain.repository.model.GifDomain
import com.mrjalal.randomgif.domain.repository.model.SingleGifDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import java.lang.Exception

class FetchRandomGifUseCaseTest {

    private val repository: GifRepository = mockk()
    private val sut = FetchRandomGifUseCase(repository)

    private val gitUiModel = GifDomain(
        gifPageUrl = "",
        url = "",
        previewUrl = "",
        id = "ID",
        rating = "G",
        title = "TITLE"
    )
    private val singleGifUiModel = SingleGifDomain(
        data = gitUiModel
    )

    @Test
    fun `fetch-random-gif-use-case return success`() {
        // Given
        val expectedResult = Result.success(singleGifUiModel)
        coEvery { repository.fetchRandomGif() } returns expectedResult

        // When
        val result = runBlocking { sut() }

        // Then
        assertEquals(expectedResult, result)
    }

    @Test
    fun `fetch-random-gif-use-case return error`() {
        // Given
        val errorMessage = "ERROR"
        val expectedError = Result.failure<SingleGifDomain>(Exception(errorMessage))
        coEvery { repository.fetchRandomGif() } returns expectedError

        // When
        val result = runBlocking { sut() }

        // Then
        assertEquals(false, result.isSuccess)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
}