package com.example.moviescatalog.data.models

data class Register(
    val birthDate: String,
    val email: String,
    val gender: Int,
    val name: String,
    val password: String,
    val userName: String
)