package com.example.myapplication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.myapplication.presentation.screens.MainScreen
import org.koin.androidx.compose.koinViewModel

const val mainScreenRoute = "mainScreen"

fun NavGraphBuilder.mainScreen(){
    composable(mainScreenRoute){
        MainScreen(vm = koinViewModel())
    }
}

fun NavHostController.navigateToMainScreen(){
    navigate(mainScreenRoute){
        popUpTo(authScreenRoute){ inclusive = true }
    }
}