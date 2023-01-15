package com.example.moviescatalog.data.dto

import com.google.gson.annotations.SerializedName

data class MoviePageDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("poster")
    val poster: String?,

    @SerializedName("year")
    val year: Int,

    @SerializedName("country")
    val country: String?,

    @SerializedName("genres")
    val genres: List<GenreDto>,

    @SerializedName("reviews")
    val reviews: List<ReviewShortDto>
)