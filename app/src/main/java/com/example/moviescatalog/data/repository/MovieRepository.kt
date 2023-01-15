package com.example.moviescatalog.data.repository

import android.util.Log
import com.example.moviescatalog.data.di.ApiService
import com.example.moviescatalog.data.di.GetMoviesDetailsCallback
import com.example.moviescatalog.data.dto.MovieDetailsDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {

    private var callGetDetails: Call<MovieDetailsDto>? = null

    fun getDetails(movieId: String, callback: GetMoviesDetailsCallback<MovieDetailsDto>) {
        callGetDetails = apiService.getDetails(movieId)
        callGetDetails?.enqueue(
            object : Callback<MovieDetailsDto> {

                override fun onResponse(
                    call: Call<MovieDetailsDto>,
                    response: Response<MovieDetailsDto>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                id = it.id,
                                name = it.name,
                                posterUrl = it.poster,
                                year = it.year,
                                country = it.country,
                                genres = it.genres.map { it.name },
                                reviews = it.reviews,
                                time = it.time,
                                tagline = it.tagline,
                                description = it.description,
                                director = it.director,
                                budget = it.budget,
                                fees = it.fees,
                                ageLimit = it.ageLimit
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

                override fun onFailure(call: Call<MovieDetailsDto>, t: Throwable) {
                    callback.onError(t.message)
                }

            }
        )
    }

}