package com.example.moviescatalog.data.models

import com.google.gson.annotations.SerializedName

data class MovieDetailsModel(
    @SerializedName("ageLimit")
    val ageLimit: Int,

    @SerializedName("budget")
    val budget: Int?,

    @SerializedName("country")
    val country: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("director")
    val director: String?,

    @SerializedName("fees")
    val fees: Int?,

    @SerializedName("genres")
    val genres: List<Genre>,

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("poster")
    val poster: String?,

    @SerializedName("reviews")
    val reviews: List<Review>,

    @SerializedName("tagline")
    val tagline: String?,

    @SerializedName("time")
    val time: Int,

    @SerializedName("year")
    val year: Int
)