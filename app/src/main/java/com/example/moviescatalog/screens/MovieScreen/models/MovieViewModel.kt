package com.example.moviescatalog.screens.MovieScreen.models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalog.data.di.GetMoviesDetailsCallback
import com.example.moviescatalog.data.models.MovieDetails
import com.example.moviescatalog.data.models.MovieDetailsModel
import com.example.moviescatalog.data.models.Review
import com.example.moviescatalog.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) // подключаем нужные репозитории
    : ViewModel() {

    private val _movieStateDate = MutableLiveData<MovieState>()
    val movieState: LiveData<MovieState>
        get() = _movieStateDate

    private val _isMovieLoadingData =  MutableLiveData<Boolean>()
    val isMovieLoading: LiveData<Boolean>
        get() = _isMovieLoadingData

    private val _isFavouriteData = MutableLiveData<Boolean>()
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

    private var movieId: String = ""

    fun getMovieisDetails() = viewModelScope.launch {
        if(movieState.value !is MovieState.Default)
            _movieStateDate.postValue(MovieState.Loading)

        _isMovieLoadingData.postValue(true)
        repository.getDetails(
            movieId = "5d50d333-73fd-4c8a-a2bb-08d9b9f3d2a2",
            object : GetMoviesDetailsCallback<MovieDetailsModel> {
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
                    _reviewsData.postValue(reviews)

                    _movieStateDate.postValue(MovieState.Default)
                    Log.d("MovieDetails", "${_nameData.value}")
                }

                override fun onError(error: String?) {
                    _movieStateDate.postValue(MovieState.UnknownError)

                    _isMovieLoadingData.postValue(false)
                    Log.d("MovieDetailsError", "$error")
                }

            })
    }

    //private var movieId: String = ""

}