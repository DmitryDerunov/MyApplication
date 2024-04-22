package com.example.myapplication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.myapplication.presentation.screens.AuthScreen
import org.koin.androidx.compose.koinViewModel

const val authScreenRoute = "authScreen"

fun NavGraphBuilder.authScreen(onLogin: () -> Unit){
    composable(authScreenRoute){
        AuthScreen(vm = koinViewModel(), onLogin = onLogin)
    }
}

fun NavHostController.navigateToAuth(){
    navigate(authScreenRoute)
}