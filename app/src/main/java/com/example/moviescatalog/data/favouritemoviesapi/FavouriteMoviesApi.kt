package com.example.moviescatalog.data.favouritemoviesapi

import retrofit2.http.POST
import retrofit2.http.Path

interface FavouriteMoviesApi {
    @POST("api/favorites/{id}/add")
    suspend fun add(@Path("id") id: String)
}