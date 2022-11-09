package com.example.moviescatalog.network

import com.example.moviescatalog.data.authapi.AuthRepository
import com.example.moviescatalog.data.authapi.RegisterRequestBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//@Composable
fun Auth() {
    val repository = AuthRepository()

    CoroutineScope(Dispatchers.IO).launch {
        repository.register(
            RegisterRequestBody(
                userName = "",
                name = "",
                password = "",
                email = "",
                birthDate = null,
                gender = 0
            )
        ).collect{
            token ->
            val a = token
        }
    }
}