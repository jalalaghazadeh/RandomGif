package com.mrjalal.randomgif.di.network.module

import com.mrjalal.randomgif.di.network.qualifier.ApiKeyQualifier
import com.mrjalal.randomgif.di.network.qualifier.BaseUrlQualifier
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @BaseUrlQualifier
    fun provideBaseUrl() = "https://api.giphy.com/"

    @Provides
    @ApiKeyQualifier
    fun provideApiKey() = "R0EwtB7exS59JghpNEieWQVxsZj6ULkl"

    @Provides
    fun provideKotlinJsonAdapterFactory() = KotlinJsonAdapterFactory()

    @Provides
    fun provideMoshi(
        kotlinJsonAdapterFactory: KotlinJsonAdapterFactory
    ): Moshi {
        return Moshi
            .Builder()
            .add(kotlinJsonAdapterFactory)
            .build()
    }

    @Provides
    fun provideConvertorFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.HEADERS).setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkHeaderInterceptor: NetworkHeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(networkHeaderInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
        @BaseUrlQualifier baseUrl: String
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(baseUrl)
            .build()
    }
}