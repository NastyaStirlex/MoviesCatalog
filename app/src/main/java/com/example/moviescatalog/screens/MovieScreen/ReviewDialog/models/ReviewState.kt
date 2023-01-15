package com.example.moviescatalog.screens.MovieScreen.ReviewDialog.models

import androidx.annotation.StringRes
import com.example.moviescatalog.R

sealed class ReviewState {
    object AddReviewSuccessfull : ReviewState()

    sealed class Error(@StringRes val id: Int) : ReviewState()
    object NetworkError : Error(R.string.network_error)
    object HttpError : Error(R.string.http_error)
    object UnknownError : Error(R.string.unknown_error)
}