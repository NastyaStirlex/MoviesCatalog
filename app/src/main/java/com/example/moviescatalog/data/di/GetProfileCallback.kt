package com.example.moviescatalog.data.di

import com.example.moviescatalog.data.models.Review

interface GetProfileCallback<T> {
    fun onSuccess(
        id: String,
        username: String,
        email: String,
        avatarLink: String?,
        name: String,
        birthday: String,
        gender: Int
    )
    fun onError(error: String?)
}