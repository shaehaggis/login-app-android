package com.example.loginapp.network.api

import com.example.loginapp.model.network.Credentials
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServer {
    @POST("/auth")
    suspend fun authenticate(@Body credentials: Credentials): Response<ResponseBody>
}