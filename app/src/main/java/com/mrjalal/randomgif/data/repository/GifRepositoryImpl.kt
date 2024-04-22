package com.mrjalal.randomgif.data.repository

import com.mrjalal.randomgif.data.dataSource.remote.GifRemoteDataSource
import com.mrjalal.randomgif.domain.repository.GifRepository
import com.mrjalal.randomgif.domain.repository.model.InquiryGifDomain
import com.mrjalal.randomgif.domain.repository.model.SingleGifDomain
import javax.inject.Inject

/**
 * Implementation of the GIF repository responsible for interacting with the data sources.
 * @param remoteDataSource The remote data source for fetching GIF-related data.
 */
class GifRepositoryImpl @Inject constructor(
    private val remoteDataSource: GifRemoteDataSource
): GifRepository {
    /**
     * Fetches GIFs matching the specified query.
     * @param q The query string used to search for GIFs.
     * @return A result containing the list of GIFs matching the query.
     */
    override suspend fun inquiryGifs(q: String): Result<InquiryGifDomain> {
        return remoteDataSource.inquiryGifs(q).map { it.asUiModel() }
    }

    /**
     * Fetches a random GIF.
     * @return A result containing the randomly fetched GIF.
     */
    override suspend fun fetchRandomGif(): Result<SingleGifDomain> {
        return remoteDataSource.fetchRandomGif().map { it.asUiModel() }
    }

    /**
     * Fetches details of a single GIF by its ID.
     * @param id The ID of the GIF to fetch details for.
     * @return A result containing the details of the specified GIF.
     */
    override suspend fun fetchSingleGif(id: String): Result<SingleGifDomain> {
        return remoteDataSource.fetchSingleGif(id).map { it.asUiModel() }
    }
}