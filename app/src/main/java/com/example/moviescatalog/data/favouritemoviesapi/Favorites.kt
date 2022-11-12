package com.example.moviescatalog.data.favouritemoviesapi

@kotlinx.serialization.Serializable
data class Favorites(
    val movies: List<Movy>
)