package com.example.moviescatalog.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviescatalog.R
import com.example.moviescatalog.data.di.ApiService
import com.example.moviescatalog.data.Event
import com.example.moviescatalog.data.dto.JwtTokenDto
import com.example.moviescatalog.data.dto.LoginBodyDto
import com.example.moviescatalog.data.dto.RegisterBodyDto
import com.example.moviescatalog.screens.ProfileScreen.models.ProfileState
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val apiService: ApiService,
    private val jwtRepository: JwtRepository,
    @ApplicationContext application: Context
) {
    val application: Context = application

    suspend fun login(
        loginBody: LoginBodyDto,
        state: MutableLiveData<Event<JwtTokenDto>>
    ) {
        try {
            state.postValue(Event.loading())

            val response = apiService.login(loginBody)

            if (response.isSuccessful) {
                state.postValue(Event.success(response.body()))

            } else if (response.errorBody() != null) {
                if(response.code() == 400)
                    state.postValue(Event.error(R.string.login_failed))
            }
        } catch (e: Exception) {
            when(e) {
                is HttpException -> {
                    state.postValue(Event.error(R.string.http_error))
                }
                is UnknownHostException, is SocketException -> {
                    state.postValue(Event.error(R.string.unknown_error))
                }
            }
            e.printStackTrace()
            state.postValue(Event.error(null))
        }
    }

    suspend fun register(
        registerBody: RegisterBodyDto,
        state: MutableLiveData<Event<JwtTokenDto>>
    ) {
        try {
            state.postValue(Event.loading())

            val response = apiService.register(registerBody)

            if (response.isSuccessful) {
                state.postValue(Event.success(response.body()))

            } else if (response.errorBody() != null) {
                Log.d("Register Error", response.code().toString() + response.errorBody())
                if(response.code() == 400)
                    state.postValue(Event.error(R.string.duplicate_username))
            }
        } catch (e: Exception) {
            when(e) {
                is HttpException -> {
                    state.postValue(Event.error(R.string.http_error))
                }
                is UnknownHostException, is SocketException -> {
                    state.postValue(Event.error(R.string.unknown_error))
                }
            }
            e.printStackTrace()
            state.postValue(Event.error(null))
        }
    }

    suspend fun logout(state: MutableLiveData<ProfileState>) {
        try {
            jwtRepository.deleteToken(context = application)
            apiService.logout()
            state.value = ProfileState.LogoutSuccessful
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    state.postValue(ProfileState.HttpError)
                }
                is UnknownHostException, is SocketException -> {
                    state.postValue(ProfileState.NetworkError)
                }
                else -> {
                    e.printStackTrace()
                    state.postValue(ProfileState.UnknownError)
                }
            }
        }
    }
}