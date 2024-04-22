package com.mrjalal.randomgif.di

import com.mrjalal.randomgif.data.dataSource.remote.GifApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    fun provideGifApi(retrofit: Retrofit): GifApi = retrofit.create(GifApi::class.java)
}