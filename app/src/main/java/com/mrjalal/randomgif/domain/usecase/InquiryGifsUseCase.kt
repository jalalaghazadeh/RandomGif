package com.mrjalal.randomgif.domain.usecase

import com.mrjalal.randomgif.domain.repository.GifRepository
import com.mrjalal.randomgif.domain.repository.model.InquiryGifDomain
import javax.inject.Inject

/**
 * Use case responsible for performing a GIF inquiry.
 * @param repository The repository responsible for handling GIF data operations.
 */
class InquiryGifsUseCase @Inject constructor(
    private val repository: GifRepository
) {


    suspend operator fun invoke(q: String): Result<InquiryGifDomain> {
        return repository.inquiryGifs(q)
    }
}