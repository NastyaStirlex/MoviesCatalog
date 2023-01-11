package com.example.moviescatalog.data.repository

import android.util.Log
import android.widget.Toast
import com.example.moviescatalog.data.ApiService
import com.example.moviescatalog.data.di.GetMoviesDetailsCallback
import com.example.moviescatalog.data.models.MovieDetails
import com.example.moviescatalog.data.models.MovieDetailsModel
import com.example.moviescatalog.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {
    private var call: Call<MovieDetailsModel>? = null


    suspend fun getDetails(movieId: String, callback: GetMoviesDetailsCallback<MovieDetailsModel>) {
        call = apiService.getDetails(movieId)
        call?.enqueue(
            object : Callback<MovieDetailsModel> {

                override fun onResponse(
                    call: Call<MovieDetailsModel>,
                    response: Response<MovieDetailsModel>
                ) {
                    if(response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let {
                        if (response.isSuccessful) {
                            Log.d("Succes", "isSuccesful")
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
                                } catch(e: Exception) {
                                    when(e) {
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

                override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {
                    Log.d("onFail", "Im here")
                    callback.onError(t.message)
                }

            }
        )
    }
//        return try {
//            val movie = apiService.getDetails(movieId)
//            Resource.Success(data = movie)
//        } catch (e: Exception) {
//            Resource.Error(message = e.message.toString())
//        }


}