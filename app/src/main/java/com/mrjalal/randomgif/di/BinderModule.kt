package com.mrjalal.randomgif.di

import com.mrjalal.randomgif.data.dataSource.remote.GifRemoteDataSource
import com.mrjalal.randomgif.data.dataSource.remote.GifRemoteDataSourceImpl
import com.mrjalal.randomgif.data.repository.GifRepositoryImpl
import com.mrjalal.randomgif.domain.repository.GifRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {
    @Binds
    abstract fun bindRepository(gifRepositoryImpl: GifRepositoryImpl): GifRepository

    @Binds
    abstract fun bindRemoteDataSource(gitRemoteDataSourceImpl: GifRemoteDataSourceImpl): GifRemoteDataSource
}