package com.example.moviescatalog.data.di

import com.example.moviescatalog.data.dto.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    //account
    @POST("api/account/register")
    suspend fun register(@Body registerBody: RegisterBodyDto): Response<JwtTokenDto>

    @POST("api/account/login")
    suspend fun login(@Body loginBody: LoginBodyDto): Response<JwtTokenDto>

    @POST("api/account/logout")
    suspend fun logout()


    // favourite movies
    @GET("api/favorites")
    fun getFavourites(@Header("Authorization") token: String): Call<FavoriteMoviesDto>

    @POST("api/favorites/{id}/add")
    suspend fun addFavourite(@Header("Authorization") token: String, @Path("id") id: String)

    @DELETE("api/favorites/{id}/delete")
    suspend fun deleteFavourite(@Header("Authorization") token: String, @Path("id") id: String)


    //movie
    @GET("api/movies/details/{id}")
    fun getDetails(@Path("id") id: String): Call<MovieDetailsDto>

    @GET("api/movies/{page}")
    suspend fun getMoviesPage(@Path("page") page: Int): Response<MoviesPageDto>

    //review
    @POST("/api/movie/{movieId}/review/add")
    suspend fun addReview(
        @Header("Authorization") token: String,
        @Path("movieId") movieId: String,
        @Body addReviewBody: AddReviewBody
    )

    @PUT("/api/movie/{movieId}/review/{id}/edit")
    suspend fun editReview(
        @Header("Authorization") token: String,
        @Path("movieId") movieId: String,
        @Path("id") id: String,
        @Body addReviewBody: AddReviewBody
    )

    @DELETE("/api/movie/{movieId}/review/{id}/delete")
    suspend fun deleteReview(
        @Header("Authorization") token: String,
        @Path("movieId") movieId: String,
        @Path("id") id: String
    )


    //profile
    @GET("api/account/profile")
    fun getProfile(@Header("Authorization") token: String): Call<ProfileDto>

    @PUT("api/account/profile")
    suspend fun updateProfile(@Header("Authorization") token: String, @Body profileBody: ProfileDto)
}