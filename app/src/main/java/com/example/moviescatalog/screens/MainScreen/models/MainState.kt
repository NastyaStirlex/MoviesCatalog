package com.example.moviescatalog.screens.MainScreen.models

import androidx.annotation.StringRes
import com.example.moviescatalog.R

sealed class MainState {
    object Loading : MainState()
    object DeleteFavouriteSuccessful : MainState()

    sealed class Error(@StringRes val id: Int) : MainState()
    object NetworkError : Error(R.string.network_error)
    object HttpError : Error(R.string.http_error)
    object UnknownError : Error(R.string.unknown_error)
}