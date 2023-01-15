package com.example.moviescatalog.screens.MovieScreen.models

import androidx.annotation.StringRes
import com.example.moviescatalog.R
import com.example.moviescatalog.screens.MainScreen.models.MainState

sealed class MovieState {
    object Loading : MovieState()
    object Default : MovieState()
    object AddFavouriteSuccesfull : MovieState()
    object DeleteReviewSuccesfull : MovieState()

    sealed class Error(@StringRes val id: Int) : MovieState()
    object NetworkError : Error(R.string.network_error)
    object HttpError : Error(R.string.http_error)
    object UnknownError : Error(R.string.unknown_error)
}