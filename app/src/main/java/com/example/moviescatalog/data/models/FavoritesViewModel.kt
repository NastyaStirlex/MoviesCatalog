package com.example.moviescatalog.data.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalog.data.favouritemoviesapi.Favorites
import com.example.moviescatalog.data.favouritemoviesapi.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val favoritesRepository: FavoritesRepository): ViewModel() {
    private val _favoritesMovies = MutableLiveData<Favorites>()
    val favoritesMovies: LiveData<Favorites>
        get() = _favoritesMovies

    fun getFavorite() {
        viewModelScope.launch {
            favoritesRepository.getFavoritesMovies().let {
                if (it.isSuccessful) {
                    _favoritesMovies.postValue(it.body())
                } else {
                    Log.d("Favorites: ", "Failed to load favorites: ${it.errorBody()}")
                }
            }
        }
    }
}