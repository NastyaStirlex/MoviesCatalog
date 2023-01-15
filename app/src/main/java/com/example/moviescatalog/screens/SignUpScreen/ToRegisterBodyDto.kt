package com.example.moviescatalog.screens.SignUpScreen

import com.example.moviescatalog.data.dto.RegisterBodyDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun toRegisterBodyDto(
    username: String,
    email: String,
    name: String,
    password: String,
    birthdate: LocalDate,
    gender: String
): RegisterBodyDto {
    return RegisterBodyDto(
        username = username,
        name = name,
        email = email,
        password = password,
        birthdate = birthdate.atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")),
        gender = if(gender == "Male") 0 else 1
    )
}