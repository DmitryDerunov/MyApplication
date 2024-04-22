package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.myapplication.navigation.authScreen
import com.example.myapplication.navigation.mainScreen
import com.example.myapplication.navigation.navigateToMainScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        authScreen(onLogin = { navController.navigateToMainScreen() })
        mainScreen()
    }
}