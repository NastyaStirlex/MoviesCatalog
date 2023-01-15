package com.example.moviescatalog.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.moviescatalog.data.di.ApiService
import com.example.moviescatalog.data.dto.AddReviewBody
import com.example.moviescatalog.screens.MovieScreen.models.MovieState
import com.example.moviescatalog.screens.MovieScreen.ReviewDialog.models.ReviewState
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val apiService: ApiService,
    private val jwtRepository: JwtRepository,
    @ApplicationContext application: Context
) {
    private val token = jwtRepository.getToken(context = application)!!

    suspend fun addReview(
        movieId: String,
        addReviewBody: AddReviewBody, state: MutableLiveData<ReviewState>
    ) {
        try {
            apiService.addReview(
                token = "Bearer $token",
                movieId = movieId,
                addReviewBody = addReviewBody
            )
            state.value = ReviewState.AddReviewSuccessfull
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    state.postValue(ReviewState.HttpError)
                }
                is UnknownHostException, is SocketException -> {
                    state.postValue(ReviewState.NetworkError)
                }
                else -> {
                    e.printStackTrace()
                    state.postValue(ReviewState.UnknownError)
                }
            }
        }
    }

    suspend fun editReview(
        movieId: String,
        reviewId: String,
        addReviewBody: AddReviewBody,
        state: MutableLiveData<ReviewState>
    ) {
        try {
            apiService.editReview(
                token = "Bearer $token",
                movieId = movieId,
                id = reviewId,
                addReviewBody = addReviewBody
            )
            state.value = ReviewState.AddReviewSuccessfull
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    state.postValue(ReviewState.HttpError)
                }
                is UnknownHostException, is SocketException -> {
                    state.postValue(ReviewState.NetworkError)
                }
                else -> {
                    e.printStackTrace()
                    state.postValue(ReviewState.UnknownError)
                }
            }
        }
    }

    suspend fun deleteReview(movieId: String, reviewId: String, state: MutableLiveData<MovieState>) {
        try {
            apiService.deleteReview(
                token = "Bearer $token",
                movieId = movieId,
                id = reviewId
            )
            state.postValue(MovieState.DeleteReviewSuccesfull)
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
}