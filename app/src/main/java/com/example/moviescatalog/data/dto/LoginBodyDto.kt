package com.example.moviescatalog.data.dto

import com.google.gson.annotations.SerializedName

data class LoginBodyDto (
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String
)