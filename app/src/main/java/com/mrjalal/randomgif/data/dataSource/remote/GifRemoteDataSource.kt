package com.mrjalal.randomgif.data.dataSource.remote

import com.mrjalal.randomgif.data.dataSource.remote.model.InquiryGifDto
import com.mrjalal.randomgif.data.dataSource.remote.model.SingleGifDto

interface GifRemoteDataSource {
    suspend fun inquiryGifs(q: String): Result<InquiryGifDto>
    suspend fun fetchRandomGif(): Result<SingleGifDto>
    suspend fun fetchSingleGif(id: String): Result<SingleGifDto>
}