package com.example.myapplication.utils

sealed interface DataError: Error {
    enum class Network: DataError {
        SERVER_ERROR,
        UNKNOWN
    }

    data object UnknownError : DataError
}