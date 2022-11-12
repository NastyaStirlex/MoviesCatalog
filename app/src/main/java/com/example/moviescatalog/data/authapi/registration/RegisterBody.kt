package com.example.moviescatalog.data.authapi.registration

import kotlinx.serialization.Contextual
import java.time.ZonedDateTime

@kotlinx.serialization.Serializable
data class RegisterBody (
    val userName: String,
    val name: String,
    val password: String,
    val email: String,
    val birthDate: String,
    val gender: Int
)