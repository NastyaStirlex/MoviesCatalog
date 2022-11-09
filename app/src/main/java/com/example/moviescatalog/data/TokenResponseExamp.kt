package com.example.moviescatalog.data


@kotlinx.serialization.Serializable
data class TokenResponseExamp (
    val accessToken: String,
    val refreshToken: String,
)