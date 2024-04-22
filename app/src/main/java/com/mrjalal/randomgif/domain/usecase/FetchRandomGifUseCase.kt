package com.mrjalal.randomgif.domain.usecase

import com.mrjalal.randomgif.domain.repository.GifRepository
import com.mrjalal.randomgif.domain.repository.model.SingleGifDomain
import javax.inject.Inject

/**
 * Use case responsible for fetching a random GIF.
 * @param repository The repository for accessing GIF data.
 */
class FetchRandomGifUseCase @Inject constructor(
    private val repository: GifRepository
) {
    suspend operator fun invoke(): Result<SingleGifDomain> {
        return repository.fetchRandomGif()
    }
}