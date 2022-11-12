package com.example.moviescatalog.data.movies

import com.example.moviescatalog.data.ApiService
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPopularMovies(page: Int) =
        apiService.getMostPopularTVShows(page)
}