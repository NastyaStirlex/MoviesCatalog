package com.example.moviescatalog.data.models

data class Movie(
    val id: String,
    val name: String?,
    val posterUrl: String?,
    val year: Int,
    val country: String?,
    val genres: List<String>,
    val rating: Double
)