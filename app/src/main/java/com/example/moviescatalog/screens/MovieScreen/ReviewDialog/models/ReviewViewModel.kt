package com.example.moviescatalog.screens.MovieScreen.ReviewDialog.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalog.data.dto.AddReviewBody
import com.example.moviescatalog.data.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository,
) : ViewModel() {

    private val _reviewStateDate = MutableLiveData<ReviewState>()
    val reviewState: LiveData<ReviewState>
        get() = _reviewStateDate

    private val _isAnonymousData = MutableLiveData(false)
    val isAnonymous: LiveData<Boolean>
        get() = _isAnonymousData

    private val _commentData = MutableLiveData<String>("")
    val comment: LiveData<String>
        get() = _commentData

    private val _ratingData = MutableLiveData<Int>(0)
    val rating: LiveData<Int>
        get() = _ratingData

    private val _correctnessFields = MutableLiveData<Boolean>(false)
    val correctnessFields: LiveData<Boolean>
        get() = _correctnessFields

    private var movieId: String = ""
    private var reviewId: String? = null

    fun ratingChange(rating: Int) {
        _ratingData.value = rating
    }

    fun commentChange(comment: String) {
        _commentData.value = comment
    }

    fun anonymousChange(anonymous: Boolean) {
        _isAnonymousData.value = anonymous
    }

    fun loadReview(
        movieId: String,
        reviewId: String?,
        comment: String,
        rating: Int,
        isAnonymous: Boolean,
    ) {
        this.movieId = movieId
        this.reviewId = reviewId
        _commentData.value = comment
        _ratingData.value = rating
        _isAnonymousData.value = isAnonymous
    }

    fun onSaveClick() = viewModelScope.launch {
        if(reviewId == null) {
            _ratingData.value?.let {
                _isAnonymousData.value?.let { it1 ->
                    AddReviewBody(
                        rating = it,
                        reviewText = _commentData.value,
                        isAnonymous = it1
                    )
                }
            }?.let { reviewRepository.addReview(movieId = movieId, it, _reviewStateDate) }
            _reviewStateDate.value = ReviewState.AddReviewSuccessfull
        } else {
            _ratingData.value?.let {
                _isAnonymousData.value?.let { it1 ->
                    AddReviewBody(
                        rating = it,
                        reviewText = _commentData.value,
                        isAnonymous = it1
                    )
                }
            }?.let {
                reviewRepository.editReview(
                    movieId = movieId,
                    reviewId = reviewId!!,
                    it,
                    _reviewStateDate
                )
            }
            _reviewStateDate.value = ReviewState.AddReviewSuccessfull
        }

    }


}