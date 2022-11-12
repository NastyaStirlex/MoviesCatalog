package com.example.moviescatalog.data.favouritemoviesapi

@kotlinx.serialization.Serializable
data class Movy(
    val country: String,
    val genres: List<GenreX>,
    val id: String,
    val name: String,
    val poster: String,
    val reviews: List<Review>,
    val year: Int
)