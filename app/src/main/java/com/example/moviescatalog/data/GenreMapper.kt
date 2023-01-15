package com.example.moviescatalog.data

import com.example.moviescatalog.data.dto.GenreDto
import com.example.moviescatalog.data.models.Genre

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id,
        name = checkNotNull(name)
    )
}