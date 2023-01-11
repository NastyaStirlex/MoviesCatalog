package com.example.moviescatalog.data.di

import com.example.moviescatalog.data.models.Review

interface GetMoviesDetailsCallback<T> {
    fun onSuccess(
        id: String,
        name: String?,
        posterUrl: String?,
        year: Int,
        country: String?,
        genres: List<String>,
        reviews: List<Review>,
        time: Int,
        tagline: String?,
        description: String?,
        director: String?,
        budget: Int?,
        fees: Int?,
        ageLimit: Int
    )
    fun onError(error: String?)
}