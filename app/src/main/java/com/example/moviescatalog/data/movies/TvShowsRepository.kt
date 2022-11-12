package com.example.moviescatalog.data.movies

import com.example.moviescatalog.data.ApiService
import dagger.Provides
import javax.inject.Inject

class TvShowsRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTvShowsDetails(tvShowId: String): TvShowsDetailsDto = apiService.getTVShowDetails(tvShowId)
}