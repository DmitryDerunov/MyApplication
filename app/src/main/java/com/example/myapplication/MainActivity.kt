package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.tokenStorage.TokenManager
import com.example.myapplication.di.appModule
import com.example.myapplication.navigation.AppNavGraph
import com.example.myapplication.navigation.authScreenRoute
import com.example.myapplication.navigation.mainScreenRoute
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    private val tokenManager: TokenManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(applicationContext)
            modules(appModule)
        }

        val startDestination = if(tokenManager.getToken().isEmpty()) authScreenRoute else mainScreenRoute

        setContent {
            val navController = rememberNavController()
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavGraph(
                        navController = navController,
                        modifier = Modifier,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}
