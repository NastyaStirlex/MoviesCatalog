package com.example.moviescatalog.data.favouritemoviesapi

@kotlinx.serialization.Serializable
data class Review(
    val id: String,
    val rating: Int
)