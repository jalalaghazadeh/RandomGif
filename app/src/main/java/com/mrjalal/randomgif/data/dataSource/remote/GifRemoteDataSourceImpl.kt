package com.mrjalal.randomgif.data.dataSource.remote

import com.mrjalal.randomgif.data.dataSource.remote.model.InquiryGifDto
import com.mrjalal.randomgif.data.dataSource.remote.model.SingleGifDto
import com.mrjalal.randomgif.di.network.qualifier.ApiKeyQualifier
import javax.inject.Inject

/**
 * Implementation of the GIF remote data source interface.
 * This class is responsible for making network requests to fetch GIF data from the API.
 * @param gifApi The Retrofit service interface for making API requests.
 * @param apiKey The API key used for authenticating requests.
 */
class GifRemoteDataSourceImpl @Inject constructor(
    private val gifApi: GifApi,
    @ApiKeyQualifier private val apiKey: String
): GifRemoteDataSource {
    /**
     * Perform a GIF inquiry request with the given [q] parameter.
     * @param q The query string for the GIF inquiry.
     * @return A Result containing the response data or an error.
     */
    override suspend fun inquiryGifs(q: String): Result<InquiryGifDto> {
        return runCatching { gifApi.inquiryGifs(apiKey, q) }
    }

    /**
     * Fetch a random GIF from the API.
     * @return A Result containing the response data or an error.
     */
    override suspend fun fetchRandomGif(): Result<SingleGifDto> {
        return runCatching { gifApi.fetchRandomGif(apiKey) }
    }

    /**
     * Fetch details of a single GIF by its [id].
     * @param id The ID of the GIF to fetch details for.
     * @return A Result containing the response data or an error.
     */
    override suspend fun fetchSingleGif(id: String): Result<SingleGifDto> {
        return runCatching { gifApi.fetchSingleGif(apiKey = apiKey, gifId = id) }
    }
}