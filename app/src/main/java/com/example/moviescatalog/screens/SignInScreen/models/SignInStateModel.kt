package com.example.moviescatalog.screens.SignInScreen.models

import androidx.annotation.StringRes
import com.example.moviescatalog.R

sealed class SignInState {
    object Default : SignInState()
    object SignInSuccessful : SignInState()

    sealed class Error(@StringRes val id: Int) : SignInState()
    object SignInFailed : Error(R.string.login_failed)
    object NetworkError : Error(R.string.network_error)
    object HttpError : Error(R.string.http_error)
    object UnknownError : Error(R.string.unknown_error)
}