package com.example.myapplication.data.repository

import com.example.myapplication.data.api.AuthApi
import com.example.myapplication.utils.DataError
import com.example.myapplication.data.models.RegisterRequest
import com.example.myapplication.utils.Result
import com.example.myapplication.data.tokenStorage.TokenManager
import retrofit2.HttpException

class AuthRepositoryImp(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager
) : AuthRepository {
    override suspend fun register(registerRequest: RegisterRequest): Result<Unit, DataError> {
        return try {
            val response = authApi.register(registerRequest)
            if (response.isSuccessful && response.body() != null){
                val responseBody = response.body()!!
                tokenManager.saveToken(responseBody.accessToken)
                Result.Success(Unit)
            } else{
                Result.Error(DataError.UnknownError)
            }

        } catch (e: HttpException) {
            when (e.code()) {
                500 -> Result.Error(DataError.Network.SERVER_ERROR)
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }
}