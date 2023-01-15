package com.example.moviescatalog.data.di

import com.example.moviescatalog.data.dto.MoviePageDto

interface GetFavouritesCallback<T> {
    fun onSuccess(
        movies: List<MoviePageDto>
    )

    fun onError(error: String?)
}