package com.example.moviescatalog.data.models

data class MovieDetails(
    val id: String,
    val name: String?,
    val posterUrl: String?,
    val year: Int,
    val country: String?,
    val genres: List<String>,
    val reviews: List<Review>,
    val time: Int,
    val tagline: String?,
    val description: String?,
    val director: String?,
    val budget: Int?,
    val fees: Int?,
    var ageLimit: Int
)