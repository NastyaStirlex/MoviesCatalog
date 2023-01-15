package com.example.moviescatalog.data.dto

import com.google.gson.annotations.SerializedName

data class JwtTokenDto (
    @SerializedName("token")
    var token: String,
)
