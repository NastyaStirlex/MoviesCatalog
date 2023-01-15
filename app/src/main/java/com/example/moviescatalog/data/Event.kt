package com.example.moviescatalog.data

import androidx.annotation.StringRes

data class Event<T>(val status: Status, val data: T?, @StringRes val error: Int?) {
    companion object {
        fun <T> loading(): Event<T> {
            return Event(Status.LOADING, null, null)
        }

        fun <T> success(data: T?): Event<T> {
            return Event(Status.SUCCESS, data, null)
        }

        fun <T> error(@StringRes error: Int?): Event<T> {
            return Event(Status.ERROR, null, error)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}