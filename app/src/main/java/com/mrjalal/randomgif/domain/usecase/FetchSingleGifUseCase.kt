package com.mrjalal.randomgif.domain.usecase

import com.mrjalal.randomgif.domain.repository.GifRepository
import com.mrjalal.randomgif.domain.repository.model.SingleGifDomain
import javax.inject.Inject

/**
 * Use case responsible for fetching details of a single GIF.
 * @param repository The repository responsible for providing data access methods.
 */
class FetchSingleGifUseCase @Inject constructor(
    private val repository: GifRepository
) {
    suspend operator fun invoke(id: String): Result<SingleGifDomain> {
        return repository.fetchSingleGif(id)
    }
}