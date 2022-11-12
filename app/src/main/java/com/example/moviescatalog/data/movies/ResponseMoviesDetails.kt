package com.example.moviescatalog.data.movies

data class ResponseMoviesDetails(
    val ageLimit: Int,
    val budget: Int,
    val country: String,
    val description: String,
    val director: String,
    val fees: Int,
    val genres: List<GenreX>,
    val id: String,
    val name: String,
    val poster: String,
    val reviews: List<ReviewX>,
    val tagline: String,
    val time: Int,
    val year: Int
)