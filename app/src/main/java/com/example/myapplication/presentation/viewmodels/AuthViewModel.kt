package com.example.myapplication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.presentation.viewStates.LoginViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    private val _onLoggedIn = Channel<Unit>()
    val onLoggedIn = _onLoggedIn.receiveAsFlow()

    private var viewState: LoginViewState = LoginViewState()
        set(value){
            field = value
            viewModelScope.launch {
                viewStateFlow.emit(value)
            }
        }

    private val viewStateFlow = MutableStateFlow(viewState)

    fun getViewState() = viewStateFlow.asStateFlow()

    fun onLoginInputChanged(login: String) {
        updateState(login = login)
    }

    fun onPasswordInoutChanged(password: String){
        updateState(password = password)
    }

    fun onLoginClick(){
        viewModelScope.launch {
            _onLoggedIn.send(Unit)
        }
    }

    private fun updateState(
        login: String = viewState.login,
        password: String = viewState.password,
        isLoading: Boolean = viewState.isLoading
    ){
        viewState = viewState.copy(
            login = login,
            password = password,
            isLoading = isLoading
        )
    }
}