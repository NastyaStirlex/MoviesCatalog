package com.example.moviescatalog.data.models

data class Movie(
    val movieId: String,
    val name: String,
    val imageUrl: String,
    val year: Int,
    val country: String,
    val genres: String,
    val rating: Float,
    val hue: Float = rating / 10f * 120
    // hue
// x               1
// 120 - green    10
)
