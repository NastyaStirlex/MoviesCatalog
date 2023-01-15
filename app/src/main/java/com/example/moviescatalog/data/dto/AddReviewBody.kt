package com.example.moviescatalog.data.dto

import com.google.gson.annotations.SerializedName

data class AddReviewBody(
    @SerializedName("rating")
    val rating: Int,

    @SerializedName("reviewText")
    val reviewText: String?,

    @SerializedName("isAnonymous")
    val isAnonymous: Boolean
)
