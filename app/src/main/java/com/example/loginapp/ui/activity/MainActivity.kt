package com.example.loginapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.loginapp.data.FirebaseAuth
import com.example.loginapp.ui.AppNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val startDestination = if (FirebaseAuth.auth.currentUser == null) {
                "login"
            } else {
                "home"
            }

            AppNavigator(navController, startDestination)
        }
    }
}