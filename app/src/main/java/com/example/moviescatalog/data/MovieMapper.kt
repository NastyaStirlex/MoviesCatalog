package com.example.moviescatalog.data

import com.example.moviescatalog.data.dto.MoviePageDto
import com.example.moviescatalog.screens.MainScreen.models.MovieModel

fun MoviePageDto.toMovie(): MovieModel {

    return  MovieModel(
        movieId = id,
        name = checkNotNull(name),
        imageUrl = checkNotNull(poster),
        year = year,
        country = checkNotNull(country),
        genres = genres.joinToString(", ") {
            it.toGenre().name
        },
        rating = reviews.map { it.rating }.average().toFloat()
    )
}