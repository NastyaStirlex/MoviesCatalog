package com.example.moviescatalog.data.data


@kotlinx.serialization.Serializable
data class TokenResponseExamp (
    val accessToken: String,
    val refreshToken: String,
)