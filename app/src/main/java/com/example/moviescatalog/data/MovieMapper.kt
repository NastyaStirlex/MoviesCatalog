package com.example.moviescatalog.data

import com.example.moviescatalog.data.dto.MoviePageDto
import com.example.moviescatalog.data.models.Movie

fun MoviePageDto.toMovie(): Movie {

    return  Movie(
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