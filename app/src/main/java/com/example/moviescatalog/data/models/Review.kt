package com.example.moviescatalog.data.models

data class Review(
    val author: ReviewAuthor,
    val createDateTime: String,
    val id: String,
    val isAnonymous: Boolean,
    val rating: Int,
    val reviewText: String
)