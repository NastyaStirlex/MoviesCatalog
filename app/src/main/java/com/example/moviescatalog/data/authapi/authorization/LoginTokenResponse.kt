package com.example.moviescatalog.data.authapi.authorization

import com.google.gson.annotations.SerializedName

data class LoginTokenResponse (
    @SerializedName("token")
    var loginToken: String,
)
