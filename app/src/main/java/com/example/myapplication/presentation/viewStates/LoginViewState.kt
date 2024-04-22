package com.example.myapplication.presentation.viewStates

data class LoginViewState(
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)