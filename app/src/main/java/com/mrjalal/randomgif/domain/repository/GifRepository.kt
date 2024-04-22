package com.mrjalal.randomgif.domain.repository

import com.mrjalal.randomgif.domain.repository.model.InquiryGifDomain
import com.mrjalal.randomgif.domain.repository.model.SingleGifDomain

interface GifRepository {
    suspend fun inquiryGifs(q: String): Result<InquiryGifDomain>
    suspend fun fetchRandomGif(): Result<SingleGifDomain>
    suspend fun fetchSingleGif(id: String): Result<SingleGifDomain>
}