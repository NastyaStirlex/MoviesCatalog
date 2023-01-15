package com.example.moviescatalog.data.dto

import com.google.gson.annotations.SerializedName

data class MoviesPageDto(
    @SerializedName("movies")
    val movies: List<MoviePageDto>,

    @SerializedName("pageInfo")
    val pageInfo: PageInfoDto
)