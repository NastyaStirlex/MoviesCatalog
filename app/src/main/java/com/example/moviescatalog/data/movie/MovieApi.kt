package com.example.moviescatalog.data.movie

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("/api/movies/{page}")
    suspend fun getMovies(@Path("page") page: Int)
}