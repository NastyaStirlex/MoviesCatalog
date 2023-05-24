package com.example.moviescatalog.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviescatalog.data.di.ApiService
import com.example.moviescatalog.data.di.GetProfileCallback
import com.example.moviescatalog.data.dto.ProfileDto
import com.example.moviescatalog.screens.ProfileScreen.models.ProfileState
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    jwtRepository: JwtRepository,
    @ApplicationContext application: Context
) {

    private val token = jwtRepository.getToken(context = application)!!

    private var callGetProfile: Call<ProfileDto>? = null

    fun getProfile(callback: GetProfileCallback<ProfileDto>) {
        callGetProfile = apiService.getProfile(token = "Bearer $token")
        callGetProfile?.enqueue(
            object : Callback<ProfileDto> {
                override fun onResponse(call: Call<ProfileDto>, response: Response<ProfileDto>) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                id = it.id,
                                username = it.username,
                                email = it.email,
                                avatarLink = it.avatarLink,
                                name = it.name,
                                birthday = it.birthday,
                                gender = it.gender,
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
                            try {
                                val errorBody = response.errorBody()
                                callback.onError(errorBody.toString())
                            } catch (e: Exception) {
                                when (e) {
                                    is HttpException -> Log.d("Exception", "HTTP exception")
                                    //e.printStackTrace()
                                }
                            }
                            //callback.onError(response.code())
                            Log.d("Error", "${callback.onError(response.errorBody().toString())}")
                            return
                            //}
                            //callback.onError("Error")
                        }
                    }
                }

                override fun onFailure(call: Call<ProfileDto>, t: Throwable) {
                    callback.onError(t.message)
                }

            }
        )
    }

    suspend fun updateProfile(profileBody: ProfileDto, state: MutableLiveData<ProfileState>) {
        try {
            state.postValue(ProfileState.SaveSuccessful)
            apiService.updateProfile(token = "Bearer $token", profileBody = profileBody)
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