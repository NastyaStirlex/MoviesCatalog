package com.example.moviescatalog.data

import com.example.moviescatalog.data.authapi.authorization.LoginBody
import com.example.moviescatalog.data.authapi.authorization.LoginTokenResponse
import com.example.moviescatalog.data.authapi.registration.RegisterBody
import com.example.moviescatalog.data.authapi.registration.RegisterTokenResponse
import com.example.moviescatalog.data.favouritemoviesapi.Favorites
import com.example.moviescatalog.data.movies.MoviesPopularBase
import com.example.moviescatalog.data.movies.TvShowsDetailsDto
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

    @GET("api/favorites")
    suspend fun getFavorites(): Response<Favorites>



    @GET("/api/movies/{page}")
    suspend fun getMostPopularTVShows(@Path("page") page: Int): Response<MoviesPopularBase>

    @GET("/api/movies/details/{id}")
    suspend fun getTVShowDetails(
        @Path("id") tvShowId: String?
    ): TvShowsDetailsDto
}