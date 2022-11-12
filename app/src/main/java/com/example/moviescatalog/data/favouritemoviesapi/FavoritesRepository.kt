package com.example.moviescatalog.data.favouritemoviesapi

import com.example.moviescatalog.data.ApiService
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val apiService: ApiService) {
        suspend fun getFavoritesMovies() = apiService.getFavorites()
}