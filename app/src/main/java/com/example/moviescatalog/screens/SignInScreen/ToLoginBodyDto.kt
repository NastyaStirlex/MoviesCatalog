package com.example.moviescatalog.screens.SignInScreen

import com.example.moviescatalog.data.dto.LoginBodyDto

fun toLoginBodyDto(login: String, password: String) : LoginBodyDto {
    return LoginBodyDto(
        username = login,
        password = password
    )
}