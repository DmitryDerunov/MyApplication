package com.example.myapplication.data.repository

import com.example.myapplication.data.api.AuthApi
import com.example.myapplication.utils.DataError
import com.example.myapplication.data.models.RegisterRequest
import com.example.myapplication.utils.Result
import com.example.myapplication.data.tokenStorage.TokenManager

interface AuthRepository {
    suspend fun register(registerRequest: RegisterRequest): Result<Unit, DataError>
}

object AuthRepositoryFactory{
    fun create(authApi: AuthApi, tokenManager: TokenManager): AuthRepository {
        return AuthRepositoryImp(authApi, tokenManager)
    }
}