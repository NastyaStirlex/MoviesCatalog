package com.example.moviescatalog.data.dto

import com.google.gson.annotations.SerializedName

data class RegisterBodyDto(
    @SerializedName("userName")
    val username: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("birthDate")
    val birthdate: String,

    @SerializedName("gender")
    val gender: Int
)