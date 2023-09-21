package com.example.loginapp.data

import com.example.loginapp.model.network.AuthResponse
import com.example.loginapp.model.network.Credentials
import com.example.loginapp.network.RetrofitClient
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository {

    suspend fun authenticate(credentials: Credentials): AuthResponse {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitClient.authServer.authenticate(credentials)
                if (response.isSuccessful) {
                    val gson = Gson()
                    gson.fromJson(response.body()?.string(), AuthResponse::class.java)
                } else {
                    AuthResponse(success = false, message = "Server error logging in")
                }
            } catch (e: Exception){
                AuthResponse(success = false, message = "Server error logging in")
            }
        }
    }
}