package com.example.moviescatalog.screens.MainScreen.models

data class MovieModel(
    val movieId: String,
    val name: String,
    val imageUrl: String,
    val year: Int,
    val country: String,
    val genres: String,
    val rating: Float,
    val hue: Float = rating / 10f * 120
)
