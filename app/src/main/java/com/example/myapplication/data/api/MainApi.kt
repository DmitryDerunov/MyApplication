package com.example.myapplication.data.api

import retrofit2.Retrofit

interface MainApi {

}

fun provideMainApi(retrofit: Retrofit): MainApi {
    return retrofit.create(MainApi::class.java)
}