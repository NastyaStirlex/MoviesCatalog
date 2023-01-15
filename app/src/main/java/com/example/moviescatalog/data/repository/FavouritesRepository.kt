package com.example.moviescatalog.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviescatalog.data.di.ApiService
import com.example.moviescatalog.data.di.GetFavouritesCallback
import com.example.moviescatalog.data.dto.FavoriteMoviesDto
import com.example.moviescatalog.screens.MainScreen.models.MainState
import com.example.moviescatalog.screens.MovieScreen.models.MovieState
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject

class FavouritesRepository @Inject constructor(
    private val apiService: ApiService,
    jwtRepository: JwtRepository,
    @ApplicationContext application: Context
) {
    private val token = jwtRepository.getToken(context = application)!!

    private var callGetFavourites: Call<FavoriteMoviesDto>? = null

    fun getFavourites(callback: GetFavouritesCallback<FavoriteMoviesDto>) {
        callGetFavourites = apiService.getFavourites(token = "Bearer $token")
        callGetFavourites?.enqueue(
            object : Callback<FavoriteMoviesDto> {

                override fun onResponse(
                    call: Call<FavoriteMoviesDto>,
                    response: Response<FavoriteMoviesDto>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if(response.isSuccessful) {
                            callback.onSuccess(
                                movies = it.movies
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

                override fun onFailure(call: Call<FavoriteMoviesDto>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }

    suspend fun addFavourite(id: String, state: MutableLiveData<MovieState>) {
        try {
            //state.postValue(MovieState.Loading)
            apiService.addFavourite(token = "Bearer $token", id = id)
            state.postValue(MovieState.AddFavouriteSuccesfull)
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    state.postValue(MovieState.HttpError)
                }
                is UnknownHostException, is SocketException -> {
                    state.postValue(MovieState.NetworkError)
                }
                else -> {
                    e.printStackTrace()
                    state.postValue(MovieState.UnknownError)
                }
            }
        }
    }

    suspend fun deleteFavourite(id: String, state: MutableLiveData<MovieState>) {
        try {
            //state.postValue(MovieState.Loading)
            apiService.deleteFavourite(token = "Bearer $token", id = id)
            state.postValue(MovieState.AddFavouriteSuccesfull)
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    state.postValue(MovieState.HttpError)
                }
                is UnknownHostException, is SocketException -> {
                    state.postValue(MovieState.NetworkError)
                }
                else -> {
                    e.printStackTrace()
                    state.postValue(MovieState.UnknownError)
                }
            }
        }
    }

    suspend fun deleteFavouriteMain(id: String, state: MutableLiveData<MainState>) {
        try {
            apiService.deleteFavourite(token = "Bearer $token", id = id)
            state.postValue(MainState.DeleteFavouriteSuccessful)
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    state.postValue(MainState.HttpError)
                }
                is UnknownHostException, is SocketException -> {
                    state.postValue(MainState.NetworkError)
                }
                else -> {
                    e.printStackTrace()
                    state.postValue(MainState.UnknownError)
                }
            }
        }
    }
}