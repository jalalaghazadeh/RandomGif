package com.mrjalal.randomgif.data.dataSource.remote

import com.mrjalal.randomgif.data.dataSource.remote.model.GifDto
import com.mrjalal.randomgif.data.dataSource.remote.model.Image
import com.mrjalal.randomgif.data.dataSource.remote.model.Images
import com.mrjalal.randomgif.data.dataSource.remote.model.InquiryGifDto
import com.mrjalal.randomgif.data.dataSource.remote.model.SingleGifDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test

class GifRemoteDataSourceImplTest {

    private val mockApi: GifApi = mockk()
    private val apiKey = "api_key"
    private val sut = GifRemoteDataSourceImpl(mockApi, apiKey)

    private val gifDto = GifDto(
        bitlyUrl = "",
        id = "ID",
        rating = "G",
        title = "TITLE",
        images = Images(
            original = Image(""),
            previewGif = Image("")
        )
    )

    private val inquiryGifDto = InquiryGifDto(
        data = listOf(gifDto)
    )

    private val singleGifDto = SingleGifDto(
        data = gifDto
    )

    // region INQUIRY_GIFS
    @Test
    fun `inquiryGifs returns success`() {
        // Given
        val query = "test"
        coEvery { mockApi.inquiryGifs(apiKey, query) } returns inquiryGifDto

        // When
        val result = runBlocking { sut.inquiryGifs(query) }

        // Then
        assertEquals(true, result.isSuccess)
        assertEquals(1, result.getOrNull()?.data?.size)
        assertEquals("ID", result.getOrNull()?.data?.firstOrNull()?.id)
    }

    @Test
    fun `inquiryGifs returns error`() {
        // Given
        val query = "test"
        coEvery { mockApi.inquiryGifs(apiKey, query) } throws RuntimeException("EXCEPTION")

        // When
        val result = runBlocking { sut.inquiryGifs(query) }

        // Then
        assertEquals(false, result.isSuccess)
        assertEquals("EXCEPTION", result.exceptionOrNull()?.message)
    }
    // endregion

    // region RANDOM_GIF

    @Test
    fun `fetchRandomGif returns success`() {
        // Given
        coEvery { mockApi.fetchRandomGif(apiKey) } returns singleGifDto

        // When
        val result = runBlocking { sut.fetchRandomGif() }

        // Then
        assertEquals(true, result.isSuccess)
        assertEquals("ID", result.getOrNull()?.data?.id)
    }

    @Test
    fun `fetchRandomGif returns error`() {
        // Given
        coEvery { mockApi.fetchRandomGif(apiKey) } throws RuntimeException("EXCEPTION")

        // When
        val result = runBlocking { sut.fetchRandomGif() }

        // Then
        assertEquals(false, result.isSuccess)
        assertEquals("EXCEPTION", result.exceptionOrNull()?.message)
    }
    // endregion

    // region SINGLE_GIF
    @Test
    fun `fetchSingleGif returns success`() {
        // Given
        val gifId = "ID"
        coEvery { mockApi.fetchSingleGif(gifId = gifId, apiKey = apiKey) } returns singleGifDto

        // When
        val result = runBlocking { sut.fetchSingleGif(id = gifId) }

        // Then
        assertEquals(true, result.isSuccess)
        assertEquals("ID", result.getOrNull()?.data?.id)
    }

    @Test
    fun `fetchSingleGif returns error`() {
        // Given
        val gifId = "ID"
        coEvery { mockApi.fetchSingleGif(gifId = gifId, apiKey = apiKey)} throws RuntimeException("EXCEPTION")

        // When
        val result = runBlocking { sut.fetchSingleGif(id = gifId) }

        // Then
        assertEquals(false, result.isSuccess)
        assertEquals("EXCEPTION", result.exceptionOrNull()?.message)
    }
    // endregion
}