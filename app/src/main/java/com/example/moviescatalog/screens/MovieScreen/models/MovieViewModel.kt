package com.example.moviescatalog.screens.MovieScreen.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalog.data.di.GetMoviesDetailsCallback
import com.example.moviescatalog.data.dto.MovieDetailsDto
import com.example.moviescatalog.data.models.Review
import com.example.moviescatalog.data.models.decodeToken
import com.example.moviescatalog.data.repository.FavouritesRepository
import com.example.moviescatalog.data.repository.JwtRepository
import com.example.moviescatalog.data.repository.MovieRepository
import com.example.moviescatalog.data.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val favouritesRepository: FavouritesRepository,
    jwtRepository: JwtRepository,
    private val reviewRepository: ReviewRepository,
    @ApplicationContext application: Context
) : ViewModel() {

    val token = jwtRepository.getToken(context = application)

    private val _movieStateDate = MutableLiveData<MovieState>()
    val movieState: LiveData<MovieState>
        get() = _movieStateDate

    private val _isMovieLoadingData = MutableLiveData<Boolean>()
    val isMovieLoading: LiveData<Boolean>
        get() = _isMovieLoadingData

    private val _isFavouriteData = MutableLiveData(false)
    val isFavouriteData: LiveData<Boolean>
        get() = _isFavouriteData

    private val _nameData = MutableLiveData<String>()
    val nameData: LiveData<String>
        get() = _nameData

    private val _movieImageUrlData = MutableLiveData<String>()
    val movieImageUrlData: LiveData<String>
        get() = _movieImageUrlData

    private val _descriptionData = MutableLiveData<String>()
    val descriptionData: LiveData<String>
        get() = _descriptionData

    private val _yearData = MutableLiveData<Int>()
    val yearData: LiveData<Int>
        get() = _yearData

    private val _countryData = MutableLiveData<String>()
    val countryData: LiveData<String>
        get() = _countryData

    private val _durationData = MutableLiveData<Int>()
    val durationData: LiveData<Int>
        get() = _durationData

    private val _taglineData = MutableLiveData<String>()
    val taglineData: LiveData<String>
        get() = _taglineData

    private val _producerData = MutableLiveData<String>()
    val producerData: LiveData<String>
        get() = _producerData

    private val _budgetData = MutableLiveData<Int>()
    val budgetData: LiveData<Int>
        get() = _budgetData

    private val _feesData = MutableLiveData<Int>()
    val feesData: LiveData<Int>
        get() = _feesData

    private val _ageData = MutableLiveData<Int>()
    val ageData: LiveData<Int>
        get() = _ageData

    private val _genresData = MutableLiveData<List<String>>()
    val genresData: LiveData<List<String>>
        get() = _genresData

    private val _reviewsData = MutableLiveData<List<Review>>()
    val reviewsData: LiveData<List<Review>>
        get() = _reviewsData

    private val _userReviewData = MutableLiveData<Review>(null)
    val userReview: LiveData<Review>
        get() = _userReviewData

    private val _otherReviewsData = MutableLiveData<List<Review>>(null)
    val otherReviews: LiveData<List<Review>>
        get() = _otherReviewsData


    private var movieID: String = ""

    fun getMoviesDetails(movieId: String, isFavorite: Boolean) = viewModelScope.launch {
        if (movieState.value !is MovieState.Default || movieID != movieId) {
            _movieStateDate.postValue(MovieState.Loading)
            movieID = movieId
        }

        _isMovieLoadingData.postValue(true)
        movieRepository.getDetails(
            movieId = movieId,
            object : GetMoviesDetailsCallback<MovieDetailsDto> {
                override fun onSuccess(
                    id: String,
                    name: String?,
                    posterUrl: String?,
                    year: Int,
                    country: String?,
                    genres: List<String>,
                    reviews: List<Review>,
                    time: Int,
                    tagline: String?,
                    description: String?,
                    director: String?,
                    budget: Int?,
                    fees: Int?,
                    ageLimit: Int
                ) {
                    _isMovieLoadingData.postValue(false)

                    _isFavouriteData.postValue(isFavorite)
                    _nameData.postValue(name)
                    _movieImageUrlData.postValue(posterUrl)
                    _descriptionData.postValue(description)
                    _yearData.postValue(year)
                    _countryData.postValue(country)
                    _durationData.postValue(time)
                    _taglineData.postValue(tagline)
                    _producerData.postValue(director)
                    _budgetData.postValue(budget)
                    _feesData.postValue(fees)
                    _ageData.postValue(ageLimit)
                    _genresData.postValue(genres)
                    _reviewsData.value = reviews
                    Log.d("Reviews: ", _reviewsData.value!![0].reviewText)

                    val decodeToken = token?.let { decodeToken(it) }

                    val uniqueName = decodeToken?.let { JSONObject(it).getString("unique_name") }
                    if (uniqueName != null) {
                        val review = isUserReview(uniqueName)
                        if (review != null) {
                            _userReviewData.value = review
                            _otherReviewsData.postValue(_reviewsData.value?.filter { it.author.nickName != uniqueName }
                                ?: emptyList())

                        } else {
                            _userReviewData.value = null
                            _otherReviewsData.postValue(_reviewsData.value)
                        }
                    }



                    _movieStateDate.postValue(MovieState.Default)
                    Log.d("MovieDetails", "${_nameData.value}")
                }

                override fun onError(error: String?) {
                    _movieStateDate.postValue(MovieState.UnknownError)

                    _isMovieLoadingData.postValue(false)
                }

            })
    }

    fun onToggleFavourite(movieId: String, isFavourite: Boolean) = viewModelScope.launch {
        if (isFavourite) {
            favouritesRepository.addFavourite(id = movieId, _movieStateDate)
            _isFavouriteData.postValue(isFavourite)
        } else {
            favouritesRepository.deleteFavourite(id = movieId, _movieStateDate)
            _isFavouriteData.postValue(isFavourite)
        }
    }

    fun isUserReview(name: String): Review? {
        return _reviewsData.value?.find { it.author.nickName == name }
    }

    fun onDeleteReviewClick() = viewModelScope.launch {
        _userReviewData.value?.id?.let {
            reviewRepository.deleteReview(
                movieId = movieID,
                reviewId = it,
                state = _movieStateDate
            )
        }
    }
}