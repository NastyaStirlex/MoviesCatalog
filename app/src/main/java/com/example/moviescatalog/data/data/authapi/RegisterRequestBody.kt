package com.example.moviescatalog.data.data.authapi

import kotlinx.serialization.Contextual
import java.time.ZonedDateTime

@kotlinx.serialization.Serializable
data class RegisterRequestBody (
    val userName: String,
    val name: String,
    val password: String,
    val email: String,
    @Contextual val birthDate: ZonedDateTime?,
    val gender: Int
)