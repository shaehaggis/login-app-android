package com.example.loginapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginapp.ui.composable.HomePageLayout
import com.example.loginapp.ui.composable.LoginScreenLayout

@Composable
fun AppNavigator(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = {
            composable(route = Screen.Login.route) {
                LoginScreenLayout(
                    navigateToHome = { navController.navigate(Screen.Home.route) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = Screen.Home.route) {
                HomePageLayout()
            }
    })
}