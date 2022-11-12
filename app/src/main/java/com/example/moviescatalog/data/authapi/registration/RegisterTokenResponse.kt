package com.example.moviescatalog.data.authapi.registration

import com.google.gson.annotations.SerializedName

data class RegisterTokenResponse (
    @SerializedName("token")
    var registerToken: String,
)