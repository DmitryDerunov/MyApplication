package com.example.myapplication.providers

import com.example.myapplication.data.tokenStorage.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpClientProvider {

    fun provideOkhttpClient(tokenManager: TokenManager): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor(Interceptor {chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization",  "Bearer ${tokenManager.getToken()}")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(newRequest)
            })
            .build()
    }
}