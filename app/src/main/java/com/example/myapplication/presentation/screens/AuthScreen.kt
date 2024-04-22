package com.example.myapplication.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.presentation.viewmodels.AuthViewModel

@Composable
fun AuthScreen(vm: AuthViewModel = viewModel(), onLogin: () -> Unit) {
    val viewState by vm.getViewState().collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        vm.onLoggedIn.collect{
            onLogin()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column {
            OutlinedTextField(value = viewState.login, onValueChange = vm::onLoginInputChanged)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = viewState.password, onValueChange = vm::onPasswordInoutChanged)
            Spacer(modifier = Modifier.height(16.dp))
            ElevatedButton(onClick = vm::onLoginClick) {
                Text(text = "Войти")
            }
        }
    }
}