package com.example.moviescatalog.data.movies

data class ReviewX(
    val author: Author,
    val createDateTime: String,
    val id: String,
    val isAnonymous: Boolean,
    val rating: Int,
    val reviewText: String
)