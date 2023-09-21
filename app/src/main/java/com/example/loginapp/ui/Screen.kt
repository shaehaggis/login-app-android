package com.example.loginapp.ui

sealed class Screen(val route: String){
    object Login: Screen("login")
    object Home: Screen("home")
}
