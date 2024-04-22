package com.example.myapplication.data.api

import com.example.myapplication.data.models.RegisterRequest
import com.example.myapplication.data.models.RegisterResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("your url")
    suspend fun register(@Body loginRequest: RegisterRequest): Response<RegisterResponse>
}

fun provideAuthApi(retrofit: Retrofit): AuthApi {
    return retrofit.create(AuthApi::class.java)
}