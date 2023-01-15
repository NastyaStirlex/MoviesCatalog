package com.example.moviescatalog.screens.SignInScreen.models

import androidx.annotation.StringRes

data class ValidationEvent(val status: Status, val field: Field?, @StringRes val error: Int?) {
    companion object {
        fun success(): ValidationEvent {
            return ValidationEvent(Status.SUCCESS, null, null)
        }

        fun error(field: Field, @StringRes error: Int?): ValidationEvent {
            return ValidationEvent(Status.ERROR, field, error)
        }

    }
}

enum class Status {
    SUCCESS,
    ERROR
}

enum class Field {
    LOGIN,
    EMAIL,
    NAME,
    PASSWORD,
    BIRTHDATE,

}