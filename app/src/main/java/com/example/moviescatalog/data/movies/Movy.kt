package com.example.moviescatalog.data.movies

data class Movy(
    val country: String,
    val genres: List<Genre>,
    val id: String,
    val name: String,
    val poster: String,
    val reviews: List<Review>,
    val year: Int
)