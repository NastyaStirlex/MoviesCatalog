package com.example.moviescatalog.screens.ProfileScreen.models

import androidx.annotation.StringRes
import com.example.moviescatalog.R

sealed class ProfileState {
    object Loading : ProfileState()
    object Default : ProfileState()
    object SaveSuccessful: ProfileState()
    object LogoutSuccessful: ProfileState()
    object AuthorizationFailed: ProfileState()

    sealed class Error(@StringRes val id: Int) : ProfileState()
    object NetworkError : Error(R.string.network_error)
    object HttpError : Error(R.string.http_error)
    object UnknownError : Error(R.string.unknown_error)
}