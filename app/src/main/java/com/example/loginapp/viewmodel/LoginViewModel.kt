package com.example.loginapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.data.LoginRepository
import com.example.loginapp.model.network.Credentials
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private var _username = mutableStateOf("")
    private var _password = mutableStateOf("")

    private var _loadingState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)

    private var loginRepository = LoginRepository()

    val username: State<String>
        get() = _username

    val password: State<String>
        get() = _password

    val loadingState: MutableStateFlow<LoginState>
        get() = _loadingState

    fun setUsername(newValue: String){
        _username.value = newValue
    }

    fun setPassword(newValue: String){
        _password.value = newValue
    }

    fun login(credentials: Credentials) {
        _loadingState.value = LoginState.Loading

        viewModelScope.launch {

            try {
                val response = loginRepository.authenticate(credentials)

                if (response.success) {
                    delay(3000)
                    _loadingState.value = LoginState.Success
                }
                else if (!response.success){
                    delay(3000)
                    _loadingState.value = LoginState.Error(response.message)
                }

            } catch(e: Exception) {
                    _loadingState.value = LoginState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}