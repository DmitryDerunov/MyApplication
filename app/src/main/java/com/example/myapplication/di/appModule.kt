package com.example.myapplication.di

import com.example.myapplication.presentation.viewmodels.AuthViewModel
import com.example.myapplication.presentation.viewmodels.MainViewModel
import com.example.myapplication.providers.OkHttpClientProvider
import com.example.myapplication.providers.RetrofitProvider
import com.example.myapplication.data.api.provideAuthApi
import com.example.myapplication.data.repository.AuthRepositoryFactory
import com.example.myapplication.data.tokenStorage.TokenManager
import com.example.myapplication.data.api.provideMainApi
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule  = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::MainViewModel)
    singleOf(::TokenManager)
    single { OkHttpClientProvider.provideOkhttpClient(get()) }
    single { RetrofitProvider.provideRetrofit(get()) }
    single { provideAuthApi(get()) }
    single { provideMainApi(get()) }
    single { AuthRepositoryFactory.create(get(), get())}
}