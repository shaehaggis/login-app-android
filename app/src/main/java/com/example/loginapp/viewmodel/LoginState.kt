package com.example.loginapp.viewmodel

sealed class LoginState {
    object Idle: LoginState()
    object Loading: LoginState()
    object Success: LoginState()
    data class Error(val errorMessage: String?): LoginState()
}
