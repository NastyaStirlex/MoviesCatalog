package com.example.moviescatalog.data.movies

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviescatalog.usecases.GetTvShowsDetailsUseCase
import com.example.moviescatalog.utils.Constants
import com.example.moviescatalog.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    moviesRepository: MoviesRepository,
    private val GetTvShowsDetailsUseCase: GetTvShowsDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val popularMovies: Flow<PagingData<Movy>> =
        Pager(PagingConfig(pageSize = 10)) {
            PopularMoviesPagingSource(moviesRepository)
        }.flow

    private val _state = mutableStateOf(TvShowDetailsState())
    val state: State<TvShowDetailsState> = _state

    init {
        savedStateHandle.get<String>(Constants.tvid.toString())?.let { tvShowId ->
            Log.e("tv", tvShowId)
            getTvShowDetails(tvShowId)
        }
    }

    private fun getTvShowDetails(tvShowId: String) {
        GetTvShowsDetailsUseCase(tvShowId).onEach { result ->
            when (result) {
                is Resource.Success<*> -> {
                    _state.value = TvShowDetailsState(tvShow = result.data)

                }
                is Resource.Error<*> -> {
                    _state.value = TvShowDetailsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading<*> -> {
                    _state.value = TvShowDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}