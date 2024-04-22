package com.mrjalal.randomgif.di.network.module

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkHeaderInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()

        newRequest.addHeader("Accept", "application/json")

        return chain.proceed(newRequest.build())
    }
}