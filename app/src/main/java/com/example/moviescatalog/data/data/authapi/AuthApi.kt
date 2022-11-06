package com.example.moviescatalog.data.data.authapi

import com.example.moviescatalog.data.data.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/account/register")
    suspend fun register(@Body body: RegisterRequestBody): TokenResponse
}