package com.example.moviescatalog.data

import com.example.moviescatalog.data.authapi.authorization.LoginBody
import com.example.moviescatalog.data.authapi.authorization.LoginTokenResponse
import com.example.moviescatalog.data.authapi.registration.RegisterBody
import com.example.moviescatalog.data.authapi.registration.RegisterTokenResponse
import com.example.moviescatalog.data.models.MovieDetails
import com.example.moviescatalog.data.models.MovieDetailsModel
import com.example.moviescatalog.utils.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService  {
    @POST("api/account/register")
    suspend fun register(@Body registerBody: RegisterBody): Response<RegisterTokenResponse>

    @POST("api/account/login")
    suspend fun login(@Body loginBody: LoginBody): Response<LoginTokenResponse>

    @GET("api/movies/details/{id}")
    fun getDetails(@Path("id") id: String): Call<MovieDetailsModel>

}