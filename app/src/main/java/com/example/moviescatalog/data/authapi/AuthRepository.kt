package com.example.moviescatalog.data.authapi

import com.example.moviescatalog.data.Network
import com.example.moviescatalog.data.TokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepository {
    private val api: AuthApi = Network.getAuthApi()

    suspend fun register(body: RegisterRequestBody): Flow<TokenResponse> = flow {
        val tokenData = api.register(body) //приходят данные после выполнения запроса
        Network.token = tokenData
        emit(tokenData) //оповещение о получении данных, эти данные появляются во flow и выполняется .collect
    }.flowOn(Dispatchers.IO)
}