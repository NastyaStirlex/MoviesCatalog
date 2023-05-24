package com.example.moviescatalog.screens.MainScreen.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviescatalog.data.di.ApiService
import com.example.moviescatalog.data.di.GetFavouritesCallback
import com.example.moviescatalog.data.dto.FavoriteMoviesDto
import com.example.moviescatalog.data.dto.MoviePageDto
import com.example.moviescatalog.data.models.Movie
import com.example.moviescatalog.data.repository.FavouritesRepository
import com.example.moviescatalog.screens.MainScreen.pagination.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService,
    private val favouritesRepository: FavouritesRepository
    ) : ViewModel() {

    private val _mainStateDate = MutableLiveData<MainState>()
    val mainStateDate: LiveData<MainState>
        get() = _mainStateDate

    private val _favouritesData = MutableLiveData<List<MoviePageDto>>()
    val favouritesData: LiveData<List<MoviePageDto>>
        get() = _favouritesData

    val galleryFlow: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 6)) {
        MoviesPagingSource(apiService = apiService)
    }.flow.cachedIn(viewModelScope)


    fun isFavoriteMovie(movieId: String): Boolean {
        val movie = _favouritesData.value?.find { it.id == movieId }
        return movie != null
    }

    fun getFavourites() = viewModelScope.launch {
        favouritesRepository.getFavourites(
            object : GetFavouritesCallback<FavoriteMoviesDto> {
                override fun onSuccess(
                    movies: List<MoviePageDto>
                ) {
                    Log.d("Favourites", movies.toString())
                    _favouritesData.value = null
                    _favouritesData.postValue(movies)

                }

                override fun onError(error: String?) {
                    _mainStateDate.postValue(MainState.UnknownError)
                }
            }
        )
    }

    fun onDeleteFavourite(movieId: String) = viewModelScope.launch {
        favouritesRepository.deleteFavouriteMain(id = movieId, state = _mainStateDate)
    }
}