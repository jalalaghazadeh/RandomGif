package com.mrjalal.randomgif.data.dataSource.remote

import com.mrjalal.randomgif.data.dataSource.remote.model.InquiryGifDto
import com.mrjalal.randomgif.data.dataSource.remote.model.SingleGifDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GifApi {
    @GET("v1/gifs/search")
    suspend fun inquiryGifs(@Query("api_key") apiKey: String, @Query("q") q: String): InquiryGifDto

    @GET("v1/gifs/random")
    suspend fun fetchRandomGif(@Query("api_key") apiKey: String): SingleGifDto

    @GET("v1/gifs/{gifId}")
    suspend fun fetchSingleGif(
        @Path("gifId") gifId: String,
        @Query("api_key") apiKey: String,
    ): SingleGifDto
}